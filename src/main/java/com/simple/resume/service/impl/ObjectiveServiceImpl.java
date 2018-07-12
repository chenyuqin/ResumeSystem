package com.simple.resume.service.impl;

import com.simple.resume.mapper.ObjectiveMapper;
import com.simple.resume.mapper.ResumeMapper;
import com.simple.resume.pojo.EduBackground;
import com.simple.resume.pojo.Objective;
import com.simple.resume.pojo.Resume;
import com.simple.resume.service.ObjectiveService;
import com.simple.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveServiceImpl implements ObjectiveService {

    @Autowired
    ObjectiveMapper objectiveMapper;

    @Override
    public Objective findByResumeId(Integer resumeId) {
        return objectiveMapper.findByResumeId(resumeId);
    }

    @Override
    public void saveObjective(Objective objective) {
        objectiveMapper.saveObjective(objective);
    }

    @Override
    public void updateObjective(Objective objective) {
        objectiveMapper.updateObjective(objective);
    }
}
