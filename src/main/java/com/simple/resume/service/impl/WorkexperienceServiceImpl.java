package com.simple.resume.service.impl;

import com.simple.resume.mapper.WorkExperienceMapper;
import com.simple.resume.pojo.Workexperience;
import com.simple.resume.service.WorkexperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkexperienceServiceImpl implements WorkexperienceService {

    @Autowired
    WorkExperienceMapper workExperienceMapper;

    @Override
    public void deleteAllByResumeId(Integer resumeId) {
        workExperienceMapper.deleteAllByResumeId(resumeId);

    }

    @Override
    public void saveWorkexperience(Workexperience workexperience) {
        workExperienceMapper.saveWorkexperience(workexperience);
    }

    @Override
    public List<Workexperience> findAllByUserID(Integer userID) {
        return workExperienceMapper.findAllByUserID(userID);
    }
}
