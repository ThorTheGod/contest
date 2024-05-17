package com.cnki.utils;

import com.cnki.pojo.RankTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AllRankOfFirstUnit {
	public static List<RankTable> allRankOfFirstUnit(String firstUnit, List<RankTable> rankTable){
		//��ȡ����һ����λ��������
		List<RankTable> rankOfFirstUnit = new ArrayList<RankTable>();
		Iterator iter;
		
		iter = rankTable.iterator();
		while(iter.hasNext()) {
			RankTable curRank = (RankTable)iter.next();
			if(curRank.getFirstUnit().equals(firstUnit))
				rankOfFirstUnit.add(curRank);
		}
		
		//����
		Collections.sort(rankOfFirstUnit, new Comparator<RankTable>() {
			public int compare(RankTable rt1, RankTable rt2) {
				   if(rt1.getScore() > rt2.getScore())
					   return -1;
				   else if(rt1.getScore() == rt2.getScore()) {
					   if(rt1.getTime() < rt2.getTime())
						   return -1;
					   else if(rt1.getTime() == rt2.getTime())
						   return 0;
					   else
						   return 1;
				   }
				   else
					   return 1;
			   }
		});
		
		//��������rankNum����
				for(int cnt = 0 ; cnt < rankOfFirstUnit.size() ; cnt++) {
					rankOfFirstUnit.get(cnt).setRankNum(cnt + 1);
				}
		
		return rankOfFirstUnit;
	}
}
