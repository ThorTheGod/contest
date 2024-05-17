package com.cnki.utils;

import java.util.ArrayList;
import java.util.List;

import com.cnki.pojo.RankTable;
import myexcel.MyExcel;

public class ReadRankTable {
	public static String PATH = "D:\\IDEA\\IdeaProject\\ssm\\src\\myFile\\RankTable.xls";
	//public static String PATH = ";
	
	public static List<RankTable> readRankTable() throws Exception{
		String PATH = ReadRankTable.PATH;
		MyExcel questionBank = new MyExcel(PATH);
		List<RankTable> rankTable = new ArrayList<RankTable>();
		
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = questionBank.getCurSheet().getLastRowNum(); //����
		for(int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			String userName = questionBank.getCell(i, 1).getStringCellValue();
			int score = Integer.valueOf(questionBank.getCell(i, 2).getStringCellValue());
			double time = Double.valueOf(questionBank.getCell(i, 3).getStringCellValue());
			String firstUnit = questionBank.getCell(i, 4).getStringCellValue();
			String secondUnit = questionBank.getCell(i, 5).getStringCellValue();
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			rankTable.add(new RankTable(uid, userName, score, time, firstUnit, secondUnit, rankNum));
		}
		
		return rankTable;
	}
	
	//�Զ���·��
	public static List<RankTable> readRankTable(String PATH) throws Exception{
		MyExcel questionBank = new MyExcel(PATH);
		List<RankTable> rankTable = new ArrayList<RankTable>();
		
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = questionBank.getCurSheet().getLastRowNum(); //����
		for(int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			String userName = questionBank.getCell(i, 1).getStringCellValue();
			int score = Integer.valueOf(questionBank.getCell(i, 2).getStringCellValue());
			double time = Double.valueOf(questionBank.getCell(i, 3).getStringCellValue());
			String firstUnit = questionBank.getCell(i, 4).getStringCellValue();
			String secondUnit = questionBank.getCell(i, 5).getStringCellValue();
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			rankTable.add(new RankTable(uid, userName, score, time, firstUnit, secondUnit, rankNum));
		}
		
		return rankTable;
	}


	public static String getRankByuserId(int userId) throws Exception {
		String rank = "-1";
		MyExcel questionBank = new MyExcel(ReadRankTable.PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = questionBank.getCurSheet().getLastRowNum(); //����
		for (int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			int uid_int = (int) uid;
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			if (uid_int == userId)
				return rankNum+"/"+rowNum;
		}
		return rank;
	}

	public static List<RankTable> getTop10User() throws Exception {
		int rank = -1;
		List<RankTable> rankTable = new ArrayList<RankTable>();
		MyExcel questionBank = new MyExcel(ReadRankTable.PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = 10;
		for(int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			String userName = questionBank.getCell(i, 1).getStringCellValue();
			int score = Integer.valueOf(questionBank.getCell(i, 2).getStringCellValue());
			double time = Double.valueOf(questionBank.getCell(i, 3).getStringCellValue());
			String firstUnit = questionBank.getCell(i, 4).getStringCellValue();
			String secondUnit = questionBank.getCell(i, 5).getStringCellValue();
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			rankTable.add(new RankTable(uid, userName, score, time, firstUnit, secondUnit, rankNum));
		}
		return rankTable;

	}

	public static List<RankTable> getTop10User(String PATH) throws Exception {
		int rank = -1;
		List<RankTable> rankTable = new ArrayList<RankTable>();
		MyExcel questionBank = new MyExcel(PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = 10;
		for(int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			String userName = questionBank.getCell(i, 1).getStringCellValue();
			int score = Integer.valueOf(questionBank.getCell(i, 2).getStringCellValue());
			double time = Double.valueOf(questionBank.getCell(i, 3).getStringCellValue());
			String firstUnit = questionBank.getCell(i, 4).getStringCellValue();
			String secondUnit = questionBank.getCell(i, 5).getStringCellValue();
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			rankTable.add(new RankTable(uid, userName, score, time, firstUnit, secondUnit, rankNum));
		}
		return rankTable;

	}

	public static List<RankTable> getTopNUser(int n) throws Exception {
		int rank = -1;
		List<RankTable> rankTable = new ArrayList<RankTable>();
		MyExcel questionBank = new MyExcel(ReadRankTable.PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = n;
		for(int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			String userName = questionBank.getCell(i, 1).getStringCellValue();
			int score = Integer.valueOf(questionBank.getCell(i, 2).getStringCellValue());
			double time = Double.valueOf(questionBank.getCell(i, 3).getStringCellValue());
			String firstUnit = questionBank.getCell(i, 4).getStringCellValue();
			String secondUnit = questionBank.getCell(i, 5).getStringCellValue();
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			rankTable.add(new RankTable(uid, userName, score, time, firstUnit, secondUnit, rankNum));
		}
		return rankTable;

	}
	public static List<RankTable> getTopNUser(int n, String PATH) throws Exception {
		int rank = -1;
		List<RankTable> rankTable = new ArrayList<RankTable>();
		MyExcel questionBank = new MyExcel(PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		int rowNum = n;
		for(int i = 1; i <= rowNum; i++) {
			long uid = Long.valueOf(questionBank.getCell(i, 0).getStringCellValue());
			String userName = questionBank.getCell(i, 1).getStringCellValue();
			int score = Integer.valueOf(questionBank.getCell(i, 2).getStringCellValue());
			double time = Double.valueOf(questionBank.getCell(i, 3).getStringCellValue());
			String firstUnit = questionBank.getCell(i, 4).getStringCellValue();
			String secondUnit = questionBank.getCell(i, 5).getStringCellValue();
			int rankNum = Integer.valueOf(questionBank.getCell(i, 6).getStringCellValue());
			rankTable.add(new RankTable(uid, userName, score, time, firstUnit, secondUnit, rankNum));
		}
		return rankTable;

	}


}
