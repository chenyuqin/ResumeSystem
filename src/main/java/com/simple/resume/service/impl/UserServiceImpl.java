package com.simple.resume.service.impl;

import com.simple.resume.mapper.UserMapper;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }
}
