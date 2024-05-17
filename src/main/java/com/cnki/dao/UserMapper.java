package com.cnki.dao;

import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //增
    int addUser(User user);
    //删
    int deleteUserByUserId(@Param("userId") int userId);
    //改
    int updateUser(User user);
    //查
    User queryUserByUsername(@Param("username") String username);
    User queryUserByUserId(@Param("userId") int userId);
    //显示全部用户
    List<User> queryAllUser();
}
