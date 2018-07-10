package com.simple.resume.mapper;

import com.simple.resume.pojo.Resume;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeMapper {
    int checkByUserID(Integer userID);
}
