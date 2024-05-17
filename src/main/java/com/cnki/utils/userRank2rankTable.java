package com.cnki.utils;

import java.util.ArrayList;
import java.util.List;

import com.cnki.pojo.RankTable;
import com.cnki.pojo.UserRank;


public class userRank2rankTable {
	public static List<RankTable> userRank2rankTable(List<UserRank> userRankList) {
		//UserRank�б�ת��ΪRankTable�б�
		List<RankTable> rankTableList = new ArrayList<RankTable>();
		for(UserRank userRank : userRankList) {
			rankTableList.add(new RankTable(userRank.getUser().getUserId(), userRank.getUser().getUsername(), userRank.getGrade().getGrade(), userRank.getGrade().getTime(), userRank.getUser().getSchool(), userRank.getUser().getCollege()));			
		}

		return rankTableList;
		
	}
}
