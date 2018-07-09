package com.simple.resume.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Workexperience {
    private Integer id;

    private Date starttime;

    private Date endtime;

    private String company;

    private String job;

    private Integer userID;

    private Integer resumeid;

    private String description;
}