package com.cnki.utils;

import com.cnki.dao.GradeMapper;
import com.cnki.pojo.RankTable;
import com.cnki.pojo.UserRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class ScheduledWriteExcel {


    @Autowired
    private GradeMapper gradeMapper;


    @Scheduled(cron="0 0/1 * * * ? ")//1分钟一次
    public void WriteExcel() throws Exception {

        Date date = new Date();
        //读取数据库
        List<UserRank> userRanks = gradeMapper.queryAllGrade();
        List<RankTable> rankTables = new ArrayList<>();
        //值注入
        rankTables = userRank2rankTable.userRank2rankTable(userRanks);
        //计算排名
        RankCalculator_total.rankCalculate_total(rankTables);
        WriteRankTable.writeRankTable(rankTables);
        System.out.println(date+":已更新排名excel表！");
    }


}
