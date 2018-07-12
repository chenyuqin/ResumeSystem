package com.simple.resume.service;

import com.simple.resume.pojo.ResumeText;
import com.simple.resume.pojo.Skill;

import java.util.List;

public interface ResumeTextService {
    ResumeText findByResumeId(Integer ResumeId);

    void saveResumeText(ResumeText resumeText);

    void updateResumeText(ResumeText resumeText);

    List<Integer> findByKeyWord(String keyWord);
}
