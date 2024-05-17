package com.cnki.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private int userId;
    private String username;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String school;
    private String college;
    private String duty;
    private String contact;
    private String email;
    private String authenticationCode;
    private String captcha;

    //rivate Grade grade;


    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(){
    }
    public User(int userId, String username, String password, Date birthday, String school, String college, String duty, String contact, String email, String authenticationCode, String captcha, Grade grade) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.school = school;
        this.college = college;
        this.duty = duty;
        this.contact = contact;
        this.email = email;
        this.authenticationCode = authenticationCode;
        this.captcha = captcha;
        //this.grade = grade;
    }

    public User(String username, String password, Date birthday, String school, String college, String duty, String contact, String email, String authenticationCode) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.school = school;
        this.college = college;
        this.duty = duty;
        this.contact = contact;
        this.email = email;
        this.authenticationCode = authenticationCode;
    }

    /**
     *
     public Grade getGrade() {
     return grade;
     }

     public void setGrade(Grade grade) {
     this.grade = grade;
     }
     * @return
     */

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }
}
