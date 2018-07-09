package com.simple.resume.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userID;

    private String userName;

    private String password;

    private Integer sex;

    private String phone;

    private String email;

    private String activeCode;

    private Integer activeStatus;

    private Integer isLogined;

    private Date createTime;

    private Date activeTime;

    private Date updateTime;

    private String description;
}
