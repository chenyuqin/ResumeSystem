package com.simple.resume.service;

import com.simple.resume.pojo.Objective;
import com.simple.resume.pojo.Resume;

import java.util.List;

public interface ObjectiveService {
    Objective findByResumeId(Integer resumeId);
}
