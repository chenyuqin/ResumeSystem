package com.simple.resume.service;

import com.simple.resume.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResumeService {
    int checkByUserID(Integer userID);
}