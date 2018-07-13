package com.simple.resume.service.impl;

import com.simple.resume.mapper.UserMapper;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        User user = userMapper.findByEmail(email);
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    //根据用户ID查询是否有对应的用户存在
    @Override
    public User findByUserID(int userID) {
        User user = userMapper.findByUserID(userID);
        return user;
    }

    //保存用户
    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    //验证登录，根据用户ID和密码验证用户输入是否正确
    @Override
    public User findByUserIDandPassword(int userID, String password) {
        User user = userMapper.findByUserIDandPassword(userID, password);
        return user;
    }

    //将用户的激活状态activeStatus设置为1（已激活）
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<Integer> findAdminLogin() {
        return userMapper.findAdminLogin();
    }
}
