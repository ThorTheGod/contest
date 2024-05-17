package com.cnki.dao;

import com.cnki.pojo.Grade;
import com.cnki.pojo.RankTable;
import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeMapper {

    //新增分数记录
    int addGrade(Grade grade);

    //某已作答用户当前排位
    Grade queryRankByUserId(@Param("userId") int userId);




    //用户信息和分数
    List<UserRank> queryAllGrade();






    List<UserRank> queryUserByGradeAndSchool(@Param("school") String school);

    List<UserRank> queryUserByGradeAndSchoolAndCollege(@Param("school") String school,@Param("college") String college);
}
