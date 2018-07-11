package com.simple.resume.VO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserInfoVO {
    private Integer userID;

    private String userName;

    private String s_sex;

    private String phone;

    private String email;

    private String description;

    private String create_time;

    private String a_activeStatus;

    //0表示未投递，1表示已投递
    private String isDeliver;
}
