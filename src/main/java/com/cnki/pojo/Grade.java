package com.cnki.pojo;

public class Grade {
    private int gradeId;
    private int userId;
    private int grade;
    private int time;

    public Grade(int userId, int grade, int time) {
        this.userId = userId;
        this.grade = grade;
        this.time = time;
    }

    public Grade(int gradeId, int userId, int grade, int time) {
        this.gradeId = gradeId;
        this.userId = userId;
        this.grade = grade;
        this.time = time;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public Grade() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
