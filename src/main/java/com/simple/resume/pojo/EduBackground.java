package com.simple.resume.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class EduBackground {
    private Integer id;

    private Date startTime;

    private Date endTime;

    private String school;

    private String major;

    private String description;

    private Integer userid;

    private Integer resumeid;
}