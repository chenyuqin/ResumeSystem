package com.simple.resume.service;

import com.simple.resume.pojo.EduBackground;
import org.springframework.stereotype.Repository;

public interface EduBackgroundService {
    EduBackground findByResumeId(Integer resumeId);

    void saveEdu(EduBackground eduBackground);

    void updateEdu(EduBackground eduBackground);
}
