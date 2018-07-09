package com.simple.resume.pojo;

import lombok.Data;

@Data
public class Skill {
    private Integer id;

    private String name;

    private Integer value;

    private Integer userID;

    private Integer resumeId;
}