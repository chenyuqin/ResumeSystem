package com.simple.resume.mapper;

import com.simple.resume.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void saveUser(User user);
}
