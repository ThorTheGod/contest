package com.cnki.utils;

import com.cnki.pojo.RankTable;

import java.util.Iterator;
import java.util.List;


public class RankCalculator_firstUnit {
	//����ָ���û���һ����λ�е�����
	public static String rankCalculate_firstUnit(long uid) throws Exception {
		int rankNum = 1;
		Iterator iter;
		String result = "";
		List<RankTable> rankTable = ReadRankTable.readRankTable();
		
		iter = rankTable.iterator();
		while(iter.hasNext()) {
			RankTable curRank = (RankTable)iter.next();
			if(curRank.getUid() == uid) 
				result = result + String.valueOf(rankNum);
			/*
			 * 
			 * 
			 * ����ʹ��uid2firstUnit�������������ݿ���Ҵ���
			 * 
			 * 
			 * 
			 */
			else if(curRank.getFirstUnit().equals(Uid2FirstUnit.uid2firstUnit(uid)))
				rankNum ++;

		}
		result = result + "/" + String.valueOf(rankNum);
		return result;
	}
	
}
