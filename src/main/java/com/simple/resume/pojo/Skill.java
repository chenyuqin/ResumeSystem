package com.simple.resume.pojo;

import lombok.Data;

@Data
public class Skill {
    private Integer id;

    private String name;

    private String value;

    private Integer userID;

    private Integer resumeId;
}