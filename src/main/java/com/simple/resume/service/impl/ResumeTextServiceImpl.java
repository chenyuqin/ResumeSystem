package com.simple.resume.service.impl;

import com.simple.resume.mapper.ResumeTextMapper;
import com.simple.resume.pojo.ResumeText;
import com.simple.resume.service.ResumeTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResumeTextServiceImpl implements ResumeTextService {

    @Autowired
    ResumeTextMapper resumeTextMapper;

    @Override
    public ResumeText findByResumeId(Integer ResumeId) {
        return resumeTextMapper.findByResumeId(ResumeId);
    }

    @Override
    public void saveResumeText(ResumeText resumeText) {
        resumeTextMapper.saveResumeText(resumeText);
    }

    @Override
    public void updateResumeText(ResumeText resumeText) {
        resumeTextMapper.updateResumeText(resumeText);
    }

    @Override
    public List<Integer> findByKeyWord(String keyWord) {
        return resumeTextMapper.findByKeyWord(keyWord);
    }
}
