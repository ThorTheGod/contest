package com.cnki.controller;

import com.cnki.pojo.RankTable;
import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import com.cnki.service.GradeService;
import com.cnki.service.UserService;
import com.cnki.utils.JsonUtils;
import com.cnki.utils.ReadRankTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 包含功能：
 * 1. /rank:排行榜页面信息显示
 * 2. /school:根据学校查排行榜
 * 3. /schoolAndCollege:根据学校和学院查排行榜
 * 4. /topTen:首页的前十名排行榜显示
 */


@Controller
public class UserRankController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("GradeServiceImpl")
    private GradeService gradeService;


    @RequestMapping("/topTen")
    @ResponseBody
    public String topTenUser() throws Exception {
        List<RankTable> rankTables = ReadRankTable.getTop10User();
        return JsonUtils.getJson(rankTables);
    }


    @RequestMapping("/html/ranking.html/top")
    @ResponseBody
    public String TopUser() throws Exception {
        List<RankTable> rankTables = ReadRankTable.getTopNUser(20);
        return JsonUtils.getJson(rankTables);
    }


    @RequestMapping("/rank")
    @ResponseBody
    //传全体UserRanks
    public String rankingList() throws Exception {

        List<UserRank> userRanks =
                gradeService.queryAllGrade();

        return JsonUtils.getJson(userRanks);
    }

    @RequestMapping("/html/administrators.html/school")
    @ResponseBody
    public String rankingListBySchool(@RequestParam("school") String school) throws Exception {

        return JsonUtils.getJson(gradeService.queryUserByGradeAndSchool(school));

    }

    @RequestMapping("/html/administrators.html/schoolAndCollege")
    @ResponseBody
    public String rankingListBySchoolAndCollege(@RequestParam("school") String school,@RequestParam("college") String college){
        return JsonUtils.getJson(gradeService.queryUserByGradeAndSchoolAndCollege(school,college));

    }

    }






