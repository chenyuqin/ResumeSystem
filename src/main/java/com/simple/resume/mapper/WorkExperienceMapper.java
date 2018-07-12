package com.simple.resume.mapper;

import com.simple.resume.pojo.Skill;
import com.simple.resume.pojo.Workexperience;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceMapper {

    List<Workexperience> findAllByUserID(Integer userID);

    void deleteAllByResumeId(Integer resumeId);

    void saveWorkexperience(Workexperience workexperience);
}
