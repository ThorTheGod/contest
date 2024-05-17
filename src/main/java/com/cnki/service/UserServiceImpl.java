package com.cnki.service;

import com.cnki.dao.UserMapper;
import com.cnki.pojo.User;
import com.cnki.pojo.UserRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUserByUserId(int id) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public User queryUserByUserId(int userId) {
        return userMapper.queryUserByUserId(userId);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }



}
