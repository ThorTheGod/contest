package com.cnki.utils;

import com.cnki.pojo.RankTable;

import java.util.List;


public class Uid2SecondUnit {
	public static String uid2SecondUnit(long uid) throws Exception {
		//����uid���Ҷ�����λ
		/*
		 * 
		 * 
		 * ʹ��ʱ�����ݿ���Ҵ���
		 * 
		 * 
		 * 
		 */
		List<RankTable> rankTable = ReadRankTable.readRankTable();
		for(int i = 0 ; i < rankTable.size() ; i++) {
			if(rankTable.get(i).getUid() == uid)
				return rankTable.get(i).getSecondUnit();
		}
		
		return "error";
	}
}
