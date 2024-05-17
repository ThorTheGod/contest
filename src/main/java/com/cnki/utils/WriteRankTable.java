package com.cnki.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cnki.pojo.RankTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import myexcel.MyExcel;

public class WriteRankTable {
	//�Զ���д��·��
	public static void writeRankTable(List<RankTable> rankTable, String PATH) throws Exception {
		//String PATH = "D:\\IDEA\\IdeaProject\\ssm\\src\\myFile\\RankTable.xls";
		
		//û���ļ��򴴽��ļ�
		File file = new File(PATH);
		if (!file.exists()) {
			try {
				//file.createNewFile();				
				Workbook workbook = new HSSFWorkbook();
				Sheet sheet = workbook.createSheet("rank");
				Row row1 = sheet.createRow(0);
				Cell cell = row1.createCell(0);
				cell.setCellValue("User ID");
				cell = row1.createCell(1);
				cell.setCellValue("User Name");
				cell = row1.createCell(2);
				cell.setCellValue("score");
				cell = row1.createCell(3);
				cell.setCellValue("time");
				cell = row1.createCell(4);
				cell.setCellValue("First Unit");
				cell = row1.createCell(5);
				cell.setCellValue("Second Unit");
				cell = row1.createCell(6);
				cell.setCellValue("rank");	
				FileOutputStream outputStream = new FileOutputStream(PATH);//��ȡ�ļ���
				workbook.write(outputStream);
				outputStream.close();				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//д���ļ�
		MyExcel questionBank = new MyExcel(PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		for(int i = 1; i <= rankTable.size(); i++) {
			RankTable rt = rankTable.get(i - 1);
			questionBank.writeExcel(i, 0, String.valueOf(rt.getUid()));
			questionBank.writeExcel(i, 1, rt.getName());
			questionBank.writeExcel(i, 2, String.valueOf(rt.getScore()));
			questionBank.writeExcel(i, 3, String.valueOf(rt.getTime()));
			questionBank.writeExcel(i, 4, rt.getFirstUnit());
			questionBank.writeExcel(i, 5, rt.getSecondUnit());
			questionBank.writeExcel(i, 6, String.valueOf(rt.getRankNum()));
		}
		//���
		FileOutputStream fileOutputStream = new FileOutputStream(questionBank.getPATH());
		questionBank.getWorkbook().write(fileOutputStream);
		fileOutputStream.close();
		
	}
	
	//Ĭ��·��
	public static void writeRankTable(List<RankTable> rankTable) throws Exception {
		String PATH = "D:\\IDEA\\IdeaProject\\ssm\\src\\myFile\\RankTables.xls";
		//String PATH = "D:\\IDEA\\IdeaProject\\ssm\\src\\myFile\\RankTable.xls";
		
		//û���ļ��򴴽��ļ�
		File file = new File(PATH);
		if (!file.exists()) {
			try {
				//file.createNewFile();				
				Workbook workbook = new HSSFWorkbook();
				Sheet sheet = workbook.createSheet("rank");
				Row row1 = sheet.createRow(0);
				Cell cell = row1.createCell(0);
				cell.setCellValue("User ID");
				cell = row1.createCell(1);
				cell.setCellValue("User Name");
				cell = row1.createCell(2);
				cell.setCellValue("score");
				cell = row1.createCell(3);
				cell.setCellValue("time");
				cell = row1.createCell(4);
				cell.setCellValue("First Unit");
				cell = row1.createCell(5);
				cell.setCellValue("Second Unit");
				cell = row1.createCell(6);
				cell.setCellValue("rank");	
				FileOutputStream outputStream = new FileOutputStream(PATH);//��ȡ�ļ���
				workbook.write(outputStream);
				outputStream.close();				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//д���ļ�
		MyExcel questionBank = new MyExcel(PATH);
		questionBank.setCurSheet(questionBank.getSheetList().get(questionBank.getMaxSheet() - 1));
		for(int i = 1; i <= rankTable.size(); i++) {
			RankTable rt = rankTable.get(i - 1);
			questionBank.writeExcel(i, 0, String.valueOf(rt.getUid()));
			questionBank.writeExcel(i, 1, rt.getName());
			questionBank.writeExcel(i, 2, String.valueOf(rt.getScore()));
			questionBank.writeExcel(i, 3, String.valueOf(rt.getTime()));
			questionBank.writeExcel(i, 4, rt.getFirstUnit());
			questionBank.writeExcel(i, 5, rt.getSecondUnit());
			questionBank.writeExcel(i, 6, String.valueOf(rt.getRankNum()));
		}
		//���
		FileOutputStream fileOutputStream = new FileOutputStream(questionBank.getPATH());
		questionBank.getWorkbook().write(fileOutputStream);
		fileOutputStream.close();
		
	}
}
