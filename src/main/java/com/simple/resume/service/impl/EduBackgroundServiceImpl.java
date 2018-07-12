package com.simple.resume.service.impl;

import com.simple.resume.mapper.EduBackgroundMapper;
import com.simple.resume.pojo.EduBackground;
import com.simple.resume.service.EduBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduBackgroundServiceImpl implements EduBackgroundService {

    @Autowired
    EduBackgroundMapper eduBackgroundMapper;

    @Override
    public EduBackground findByResumeId(Integer resumeId) {
        return eduBackgroundMapper.findByResumeId(resumeId);
    }

    @Override
    public void saveEdu(EduBackground eduBackground) {
        eduBackgroundMapper.saveEdu(eduBackground);

    }

    @Override
    public void updateEdu(EduBackground eduBackground) {
        eduBackgroundMapper.updateEdu(eduBackground);

    }
}
