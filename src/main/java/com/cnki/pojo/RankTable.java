package com.cnki.pojo;


public class RankTable {
	//�ɼ�����
	long uid;
	String name;
	int score;
	double time;
	String firstUnit;
	String secondUnit;
	int rankNum = -1;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}

	public int getRankNum() {
		return rankNum;
	}

	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}

	public RankTable() {
		
	}
	
	public RankTable(long uid, int score, double time, String firstUnit, String secondUnit) {
		//��������������,����ʼ��name
		this.uid = uid;
		this.score = score;
		this.time = time;
		this.firstUnit = firstUnit;
		this.secondUnit = secondUnit;
	}
	
	public RankTable(long uid, String name, int score, double time, String firstUnit, String secondUnit) {
		//��������������,��ʼ��name
		this.uid = uid;
		this.name = name;
		this.score = score;
		this.time = time;
		this.firstUnit = firstUnit;
		this.secondUnit = secondUnit;
	}
	
	public RankTable(long uid, String name, int score, double time, String firstUnit, String secondUnit, int rankNum) {
		//��������������,��ʼ��name
		this.uid = uid;
		this.name = name;
		this.score = score;
		this.time = time;
		this.firstUnit = firstUnit;
		this.secondUnit = secondUnit;
		this.rankNum = rankNum;
	}
}
