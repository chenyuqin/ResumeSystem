package com.simple.resume.service;

import com.simple.resume.pojo.Resume;
import com.simple.resume.pojo.Skill;

import java.util.List;

public interface SkillService {
    List<Integer> findUserIDByName(String name);

    List<Skill> findAllByUserID(Integer userID);

    void deleteAllByResumeId(Integer resumeId);

    void saveSkill(Skill skill);
}
