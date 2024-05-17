package com.cnki.controller;

import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import com.cnki.service.GradeService;
import com.cnki.service.UserService;
import com.cnki.utils.JsonUtils;
import com.cnki.utils.RankCalculator_firstUnit;
import com.cnki.utils.RankCalculator_secondUnit;
import com.cnki.utils.StateCodeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

/**
 * 功能：
 * 1./register 注册
 * 2./login 登录
 * 3./checkname 用户名查重
 * 4./sendMail 邮箱验证  ?email=1225250863@qq.com
 * 5./userInfo 查看个人信息
 * 6./logout 注销（退出登录状态
 */



@Controller
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("GradeServiceImpl")
    private GradeService gradeService;



    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.smtp.username}")
    private String emailFrom;


    //判断登录状态
    @RequestMapping(value = {"/ifLogined","/html/register.html/ifLogined",
            "/html/mainweb.html/ifLogined","/html/administrator/ifLogined",
            "/html/about.html/ifLogined","/html/ranking.html/ifLogined","/html/competition.html/ifLogined",
            "/html/personal-center.html/ifLogined",
            "/html/result.html/ifLogined"})
    @ResponseBody
    public String getUser(HttpSession session) throws JsonProcessingException {
        System.out.println("判断了登录状态");
        User user = (User)session.getAttribute("userInfo");
        if(user==null)
            return StateCodeUtils.getStateCode(0);
        else if(user.getUsername().equals("admin")){
            return JsonUtils.StringAddStateCodeJson(JsonUtils.keyValueToJson("username",user.getUsername()),2);
        }
        return JsonUtils.StringAddStateCodeJson(JsonUtils.keyValueToJson("username",user.getUsername()),1);//已有用户
    }

    @RequestMapping(value = {"/html/register.html/register","/html/mainweb.html/register","/html/administrator.html/register","/html/about.html/register"})
    @ResponseBody
    public String register(HttpSession session,User user
                           ) throws JsonProcessingException {
        String trueCaptcha = (String)session.getAttribute("captcha");
//        if(!trueCaptcha.equals(user.getCaptcha())){
//            return StateCodeUtils.getStateCode(-1);  //邮箱验证码错误，返回状态码-1
//        }
        System.out.println("触发注册");
        userService.addUser(user);
        return StateCodeUtils.getStateCode(1);  //注册通过，返回状态码1
    }

    //登录
    @RequestMapping(value = {"/login","/html/register.html/login","/html/mainweb.html/login",
            "/html/about.html/login","/html/administrator.html/login","/html/ranking.html/login"})
    @ResponseBody
    public String login(HttpSession session,@RequestParam String username,
                        @RequestParam("pwd") String password) throws JsonProcessingException {
        System.out.println("触发了login");

        if(username.contains(" ")||password.contains(" ")) {
            return StateCodeUtils.getStateCode(0);  //登录状态错误
        }
        User user = userService.queryUserByUsername(username);
        if(user==null){
            return StateCodeUtils.getStateCode(-1);//用户名不存在
        }else if (user.getPassword().equals(password)){
            if(username.equals("admin")&&password.equals("admin")){
                return StateCodeUtils.getStateCode(2);  //管理员登录，跳转到后台
            }else{
                //将密码数据设为空，不更改数据库的返回给前端，提高前端数据安全性
                user.setPassword("");
                session.setAttribute("userInfo",user);
                return JsonUtils.ObjectAddStateCodeJson(user,1); //普通用户登录
            }
        }else
            return StateCodeUtils.getStateCode(-2);//用户名存在但密码错误
    }


    @RequestMapping("/html/register.html/checkname")
    @ResponseBody
    //注册时用户名查重
    public String usernameAuthentication(String username){
        User user = userService.queryUserByUsername(username);
        if(username.contains(" ")){
            return StateCodeUtils.getStateCode(-1); //用户名非法，返回-1
        }//
        if(user==null)
            return StateCodeUtils.getStateCode(1);  //用户名可用，返回1
        else
            return StateCodeUtils.getStateCode(0);  //用户名不可用，返回0
    }
    //注册的邮箱验证 qztcjbclvqtqdhhb
    @RequestMapping(value = "/html/register.html/sendMail",produces = "application/json;" +
            "charset=utf-8")
    @ResponseBody
    public String emailAuthentication(HttpSession httpSession,
                                      @RequestParam String email){
        System.out.println("触发验证码");
        //生成验证码
        String captcha = String.valueOf(new Random().nextInt(89999)+100000);
        //session中加入验证码用于核对
        httpSession.setAttribute("Captcha",captcha);
        //发送邮件
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(emailFrom);
        msg.setTo(email);
        msg.setSubject("竞赛平台注册验证");
        msg.setText("欢迎注册竞赛平台账号，您的临时验证码为："+captcha+"，请尽快使用验证码");
        javaMailSender.send(msg);

        return StateCodeUtils.getStateCode(1);  //发送成功
    }


    //查看用户个人信息
    @RequestMapping(value = {"/html/personal-center.html/userInfo","/html/mainweb.html/userInfo","/html/about.html/userInfo","/html" +
            "/ranking.html/userInfo","/html/administrator.html/userInfo"})
    @ResponseBody
    public String getUserInfoAndRank(HttpSession session) throws Exception {

        User user = (User)session.getAttribute("userInfo");
        //查询用户，添加排名信息
        UserRank userRank = gradeService.queryRankByUserId(user
                .getUserId());
        //添加用户信息
        userRank.setUser(user);
        //转为json
        String json = JsonUtils.getJson(userRank);
        //添加学校内排名信息
        json = JsonUtils.StringAddKeyValue(json,"first",
                RankCalculator_firstUnit.rankCalculate_firstUnit(user
                        .getUserId()));
        //添加学校内学院里的排名信息
        json = JsonUtils.StringAddKeyValue(json,"second",
                RankCalculator_secondUnit.rankCalculate_secondUnit(user
                        .getUserId()));
        //
        System.out.println("userInfo:"+json);
        session.setAttribute("userInfo",json);
        return json;
    }


    //退出
    @RequestMapping(value = {"/logout","/html/personal-center.html/logout","/html/mainweb.html/logout",
            "/html/about.html/logout","/html/ranking.html/logout","/html/administrator.html/logout"})
    @ResponseBody
    public String logout(HttpSession session){
        session.invalidate();
        return StateCodeUtils.getStateCode(1);  //成功退出
    }
}
