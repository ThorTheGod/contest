package com.cnki.service;

import com.cnki.pojo.Grade;
import com.cnki.pojo.RankTable;
import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeService {



    //新增分数记录
    int addGrade(Grade grade);
    //查某用户当前全体排位
    UserRank queryRankByUserId(int userId) throws Exception;

    //查完整分数表
    List<UserRank> queryAllGrade() throws Exception;



    //查询某校成绩由高到低排名
    List<UserRank> queryUserByGradeAndSchool(String school) throws Exception;

    //查询某校某院成绩由高到低排名
    List<UserRank> queryUserByGradeAndSchoolAndCollege(String school,
                                                       String college);




}
