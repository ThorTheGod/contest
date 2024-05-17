package com.cnki.pojo;


public class UserRank{
    private User user;

    private Grade grade;

    private String rank;

    public UserRank() {
    }
    public UserRank(int grade,int time) {
        this.grade.setGrade(grade);
        this.grade.setTime(time);
    }


    public String getRank() {
        return rank;
    }
    public Grade getGrade() {
        return grade;
    }
    public User getUser() {
        return user;
    }
    public UserRank(User user, Grade grade) {
        this.user = user;
        this.grade = grade;
    }
    public UserRank(User user, Grade grade, String rank) {
        this.user = user;
        this.grade = grade;
        this.rank = rank;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRank(Grade grade, String rank) {
        this.grade = grade;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "UserRank{" +
                "grade=" + grade +
                ", rank='" + rank + '\'' +
                '}';
    }



    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    public void setRank(String rank) {
        this.rank = rank;
    }
}
