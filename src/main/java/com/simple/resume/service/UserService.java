package com.simple.resume.service;

import com.simple.resume.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface UserService {
    void saveUser(User user);
    int findByUserID(int userID);
    User findByUserIDandPassword(@Param("userID") int userID, @Param("password") String password);
    User findByEmail(String email);
    void updateUser(User user);
}
