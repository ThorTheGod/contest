package com.cnki.utils;

import com.cnki.pojo.RankTable;
import com.cnki.pojo.UserRank;

import java.util.List;


public class rankTable2userRank {
	public static void rankTable2userRank(List<RankTable> rankTableList, List<UserRank> userRankList) {
		//RankTable�б�����ע��UserRank�б�����
		//UserRank�б���Դ���RankTable�б�����RankTable�б���û�е��û�
		for(RankTable rankTable : rankTableList) {
			int uid = (int)rankTable.getUid();
			String rank = String.valueOf(rankTable.getRankNum());
			for(int i = 0; i < userRankList.size(); i++) {
				if(userRankList.get(i).getUser().getUserId() == uid) {
					UserRank tmp = userRankList.get(i);
					tmp.setRank(rank);
					userRankList.set(i, tmp);
				}
			}
		}
	}
}
