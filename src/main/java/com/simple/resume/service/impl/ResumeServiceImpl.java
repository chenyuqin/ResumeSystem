package com.simple.resume.service.impl;

import com.simple.resume.mapper.ResumeMapper;
import com.simple.resume.pojo.Resume;
import com.simple.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeMapper resumeMapper;

    @Override
    public int checkByUserID(Integer userID) {
        return resumeMapper.checkByUserID(userID);
    }

    @Override
    public List<Resume> findAllByStatus(Integer status) {
        return resumeMapper.findAllByStatus(status);
    }

    @Override
    public Resume findByUserID(Integer userID) {
        return resumeMapper.findByUserID(userID);
    }
}
