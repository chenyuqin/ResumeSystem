package com.simple.resume.mapper;

import com.simple.resume.pojo.Resume;
import com.simple.resume.pojo.Skill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillMapper {
    List<Integer> findUserIDByName(String name);

    List<Skill> findAllByUserID(Integer userID);
}
