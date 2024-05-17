package com.cnki.utils;

import com.cnki.pojo.RankTable;

import java.util.List;


public class Uid2FirstUnit {
	public static String uid2firstUnit(long uid) throws Exception {
		//����uid����һ����λ
		/*
		 * 
		 * 
		 * ʹ��ʱ�����ݿ��㷨����
		 * 
		 * 
		 * 
		 */
		List<RankTable> rankTable = ReadRankTable.readRankTable();
		for(int i = 0 ; i < rankTable.size() ; i++) {
			if(rankTable.get(i).getUid() == uid)
				return rankTable.get(i).getFirstUnit();
		}
		
		return "error";
	}
}
