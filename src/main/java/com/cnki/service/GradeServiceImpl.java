package com.cnki.service;

import com.cnki.dao.GradeMapper;
import com.cnki.pojo.Grade;
import com.cnki.pojo.UserRank;
import com.cnki.utils.*;
import com.cnki.pojo.RankTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    public void setGradeMapper(GradeMapper gradeMapper) {
        this.gradeMapper = gradeMapper;
    }

    @Override
    public int addGrade(Grade grade) {
        return gradeMapper.addGrade(grade);
    }

    @Override
    //根据用户id查出分数和排名
    public UserRank queryRankByUserId(int userId) throws Exception {
        //1.查询分数
        Grade grade = gradeMapper.queryRankByUserId(userId);
        //2.查询排名
        String rank=String.valueOf(ReadRankTable.getRankByuserId(userId));

        return new UserRank(grade,rank);
    }

    @Override
    public List<UserRank> queryAllGrade() throws Exception {
        //1.从每1分钟刷新一次的成绩excel表中获得ranktables
        List<RankTable> rankTables = ReadRankTable.readRankTable();
        //2.ranktables注入userRanks
        List<UserRank> userRanks = gradeMapper.queryAllGrade();
        rankTable2userRank.rankTable2userRank(rankTables,userRanks);
        return userRanks;
    }

    @Override
    public List<UserRank> queryUserByGradeAndSchool(String school) throws Exception {
        //1.读取数据库拿到userRank
        List<UserRank> userRanks = gradeMapper.queryUserByGradeAndSchool(school);
        //2.userRanks注入ranktable
        List<RankTable> rankTables =
                userRank2rankTable.userRank2rankTable(userRanks);
        //3.计算并返回排序后的ranktable
        rankTables = AllRankOfFirstUnit.allRankOfFirstUnit(school,rankTables);
        //4.ranktable注入回userRank
        rankTable2userRank.rankTable2userRank(rankTables,userRanks);

        return userRanks;
    }

    @Override
    public List<UserRank> queryUserByGradeAndSchoolAndCollege(String school,
                                                       String college) {
        //1.读取数据库拿到userrank
        List<UserRank> userRanks =
                gradeMapper.queryUserByGradeAndSchoolAndCollege(school
                ,college);
        //2.userRanks注入ranktable
        List<RankTable> rankTables =
                userRank2rankTable.userRank2rankTable(userRanks);
        //3.计算并返回排序后的ranktable
        rankTables = AllRankOfSecondUnit.allRankOfSecondUnit(school,college,
                rankTables);
        //4.ranktable注入回userRank
        rankTable2userRank.rankTable2userRank(rankTables,userRanks);
        return userRanks;
    }






}
