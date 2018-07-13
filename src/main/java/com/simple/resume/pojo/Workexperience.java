package com.simple.resume.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 项目经验类，与简历一对多
 */
@Data
public class Workexperience {
    private Integer id;

    private String projectName;

    private String projectDesc;

    private String roleDesc;

    private Integer userID;

    private Integer resumeId;
}