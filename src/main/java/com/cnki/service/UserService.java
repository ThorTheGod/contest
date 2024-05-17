package com.cnki.service;

import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;

import java.util.List;

public interface UserService {
    //增
    int addUser(User user);
    //删
    int deleteUserByUserId(int id);
    //改
    int updateUser(User user);
    //根据用户名查询
    User queryUserByUsername(String username);
    //根据用户Id查询
    User queryUserByUserId(int userId);
    //显示全部用户
    List<User> queryAllUser();

}
