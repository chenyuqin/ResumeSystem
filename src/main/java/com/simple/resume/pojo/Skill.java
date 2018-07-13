package com.simple.resume.pojo;

import lombok.Data;

/**
 * 技能类，与简历一对多
 */
@Data
public class Skill {
    private Integer id;

    private String name;

    private String value;

    private Integer userID;

    private Integer resumeId;
}