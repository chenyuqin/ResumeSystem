package com.simple.resume.mapper;

import com.simple.resume.pojo.EduBackground;
import com.simple.resume.pojo.Objective;
import com.simple.resume.pojo.Resume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectiveMapper {
    Objective findByResumeId(Integer resumeId);

    void saveObjective(Objective objective);

    void updateObjective(Objective objective);
}
