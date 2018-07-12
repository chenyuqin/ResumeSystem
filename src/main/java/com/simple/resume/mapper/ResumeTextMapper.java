package com.simple.resume.mapper;

import com.simple.resume.pojo.ResumeText;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeTextMapper {
    ResumeText findByResumeId(Integer ResumeId);

    void saveResumeText(ResumeText resumeText);

    void updateResumeText(ResumeText resumeText);

    List<Integer> findByKeyWord(String keyWord);
}
