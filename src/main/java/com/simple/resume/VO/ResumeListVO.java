package com.simple.resume.VO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResumeListVO {
    private Integer userID;

    private String userName;

    private String s_sex;

    private String phone;

    private String email;

    private Integer experience;

    private String deliver_time;

    private String position;

    private String ondutytime;

}
