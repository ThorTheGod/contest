package com.cnki.utils;

import com.cnki.pojo.RankTable;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class RankCalculator_total {
	//���ܳɼ�������
	public static void rankCalculate_total(List<RankTable> rankTable) throws Exception {
		//�Գɼ�������
		Collections.sort(rankTable, new Comparator<RankTable>() {
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
		for(int cnt = 0 ; cnt < rankTable.size() ; cnt++) {
			rankTable.get(cnt).setRankNum(cnt + 1);
		}
		//д��
	}
}
