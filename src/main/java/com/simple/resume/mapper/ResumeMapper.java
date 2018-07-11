package com.simple.resume.mapper;

import com.simple.resume.pojo.Resume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeMapper {
    int checkByUserID(Integer userID);

    List<Resume> findAllByStatus(Integer status);

    Resume findByUserID(Integer userID);
}
