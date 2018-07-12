package com.simple.resume.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Workexperience {
    private Integer id;

    private String projectName;

    private String projectDesc;

    private String roleDesc;

    private Integer userID;

    private Integer resumeId;
}