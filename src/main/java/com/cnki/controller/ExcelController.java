package com.cnki.controller;


import answeredQuesionType.SingleSelection_answered;
import com.cnki.pojo.Grade;
import com.cnki.pojo.SingleAndJudge;
import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import com.cnki.service.ExcelService;
import com.cnki.service.GradeService;
import com.cnki.utils.GetProblemByPageNum;
import com.cnki.utils.JsonUtils;
import com.cnki.utils.StateCodeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import questionType.Judgment_front;
import questionType.SingleSelection_front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 *功能：
 * 1./create:导入题库并生成单选和判断题库
 * 2./excel:生成随机答卷（json格式）传给前端
 * 3./judge:接收用户答卷，返回正确错误，计算分数并存入数据库
 * 4./upload 管理员上传题库
 * 5./download 管理员下载排行
 *
 *
 *
 * 主要问题：
 * 1.数据显示
 * 2.跳转
 * 3.功能还在完善；日志生成
 * 4.对接
 */



@Controller
public class ExcelController {

    @Autowired
    @Qualifier("ExcelServiceImpl")
    ExcelService excelService;

    @Autowired
    @Qualifier("GradeServiceImpl")
    GradeService gradeService;




    @RequestMapping("/create")
    @ResponseBody
    public String createDatabase() throws Exception {
        //在导入题库时执行本方法以自动生成单选和判断题库
        excelService.database();

        return StateCodeUtils.getStateCode(1);
    }

    //用户点击开始答题，生成答卷
    @RequestMapping("/html/answer.html/excel")
    @ResponseBody
    public String readyToAnswer(HttpSession session) throws Exception {

        //上面的要删！
        User user = (User)session.getAttribute("userInfo");
        if(user==null)  {       //
            return StateCodeUtils.getStateCode(0);
        }    //未登录
        else{
            int userId = user.getUserId();
            UserRank userRank = gradeService.queryRankByUserId(userId);
            if(userRank.getGrade()==null){//已登录但未答过题，可以开始答题
                //生成不包含答案的试卷
                SingleAndJudge singleAndJudge =
                        new SingleAndJudge(excelService.getSingle(),excelService.getJudge());
                //将试卷放入session
                session.setAttribute("paper",singleAndJudge);
                return StateCodeUtils.getStateCode(1);
            }
            else    //已登录且已答过题
                return StateCodeUtils.getStateCode(-1); //已登录且已答过题，返回"请勿重复参赛"
        }
    }

    @RequestMapping("/html/answer.html/question")
    @ResponseBody
    //传试卷里分页展示的题
    public String getQuestion(HttpSession session,@RequestParam("id") int id){
        //从session中拿题
        SingleAndJudge singleAndJudge = (SingleAndJudge) session.getAttribute("paper");
        System.out.println(id+"id,触发了拿十题:"+JsonUtils.getJson(GetProblemByPageNum.getProblems(singleAndJudge,
                id)));
        //根据页数id从题里取出将展示的10道题
        return JsonUtils.getJson(GetProblemByPageNum.getProblems(singleAndJudge,id));
    }



    @RequestMapping("/html/result.html/getJudge")
    public String getJudge(HttpSession session){
        List<String> afterJudge = (List<String>) session.getAttribute("afterJudge");
        System.out.println("getJudge:"+afterJudge);
        return JsonUtils.getJson(afterJudge);
    }


    //自动判卷并传回正误和分数  //@RequestParam int time,
    @RequestMapping("/html/competition.html/judge")
    @ResponseBody
    public String judgeScore(@RequestParam("result") String answer,HttpSession session) throws Exception {
        //得到answer序列号并以逗号分隔为字符串数组
        System.out.println(answer);
        String[] x = answer.split(",");
        //int time = Integer.parseInt(x[x.length-1]);
        int time = 100;
        //创建一个空的格式化答卷
        List<SingleSelection_answered> singleSelection_answereds = new ArrayList<>();
        //从session中拿到原试卷
        SingleAndJudge singleAndJudge = (SingleAndJudge) session.getAttribute("paper");
        //将原试卷的编号，和answer数组匹配
        for (int i = 0; i < 40; i++) {
            System.out.println("x["+i+"]:"+x[i]);
            if(i<=19)
                singleSelection_answereds.add(new SingleSelection_answered(singleAndJudge.getSingleSelection_fronts().get(i).getOrderOfOriBank(),x[i]));
            if (i>19)
                singleSelection_answereds.add(new SingleSelection_answered(singleAndJudge.getJudgment_fronts().get(i-20).getOrderOfOriBank(),x[i]));
        }
        System.out.println("beforeJudge:"+singleSelection_answereds);
        List<String> afterJudge = new ArrayList<>();
        afterJudge= excelService.getScore(singleSelection_answereds);
        //填入分数对象
        session.setAttribute("afterJudge",afterJudge);
        System.out.println("afterJ:"+afterJudge);


        Grade grade = new Grade();
        User user = ((User)session.getAttribute("userInfo"));
        grade.setUserId(user.getUserId());
        grade.setGrade(Integer.parseInt(afterJudge.get(afterJudge.size()-1)));
        grade.setTime(time);
        //数据库新增分数信息
        gradeService.addGrade(grade);
        return StateCodeUtils.getStateCode(1);  //考试成功
    }

    @RequestMapping("/html/upload.html/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file,
                             HttpServletRequest request) throws Exception {
        String uploadFileName = file.getOriginalFilename();

        if("".equals(uploadFileName))
            return "../html/fileUpload.html";

        String path = "D:\\IDEA\\IdeaProject\\ssm\\src\\myFile";
        File realPath = new File(path);
        if(!realPath.exists()){
            realPath.mkdir();
        }
        InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(new File(realPath,
                uploadFileName));
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
            os.flush();
        }
        os.close();
        is.close();
        //拿到原始题库，生成单选和判断题库
        excelService.database();

        return StateCodeUtils.getStateCode(1);
    }


    @RequestMapping("/html/ranking.html/download")
    @ResponseBody
    //提供给管理员下载排名excel的接口
    public String download(HttpServletResponse response,HttpServletRequest request) throws IOException {
        System.out.println("触发下载");
        String path = "D:\\IDEA\\IdeaProject\\ssm\\src\\myFile";
        String fileName = "RankTable.xls";
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path,fileName);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return StateCodeUtils.getStateCode(1);  //生成文件成功

    }



}
