package com.simple.resume.service.impl;

import com.simple.resume.mapper.ResumeMapper;
import com.simple.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeMapper resumeMapper;

    @Override
    public int checkByUserID(Integer userID) {
        return resumeMapper.checkByUserID(userID);
    }
}
