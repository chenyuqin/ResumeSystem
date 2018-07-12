package com.simple.resume.pojo;

import lombok.Data;

//用于全文检索
@Data
public class ResumeText {
    private Integer id;

    private Integer resumeId;

    private Integer userID;

    private String info;
}
