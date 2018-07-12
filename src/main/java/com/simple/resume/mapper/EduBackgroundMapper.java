package com.simple.resume.mapper;

import com.simple.resume.pojo.EduBackground;
import com.simple.resume.pojo.Resume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EduBackgroundMapper {
    EduBackground findByResumeId(Integer resumeId);

    void saveEdu(EduBackground eduBackground);

    void updateEdu(EduBackground eduBackground);
}
