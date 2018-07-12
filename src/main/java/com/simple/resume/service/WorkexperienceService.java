package com.simple.resume.service;

import com.simple.resume.pojo.Skill;
import com.simple.resume.pojo.Workexperience;

import java.util.List;

public interface WorkexperienceService {

    void deleteAllByResumeId(Integer resumeId);

    void saveWorkexperience(Workexperience workexperience);

    List<Workexperience> findAllByUserID(Integer userID);
}
