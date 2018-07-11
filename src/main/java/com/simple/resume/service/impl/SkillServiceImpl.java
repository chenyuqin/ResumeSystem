package com.simple.resume.service.impl;

import com.simple.resume.mapper.ResumeMapper;
import com.simple.resume.mapper.SkillMapper;
import com.simple.resume.pojo.Resume;
import com.simple.resume.pojo.Skill;
import com.simple.resume.service.ResumeService;
import com.simple.resume.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillMapper skillMapper;

    @Override
    public List<Integer> findUserIDByName(String name) {
        return skillMapper.findUserIDByName(name);
    }

    @Override
    public List<Skill> findAllByUserID(Integer userID) {
        return skillMapper.findAllByUserID(userID);
    }
}
