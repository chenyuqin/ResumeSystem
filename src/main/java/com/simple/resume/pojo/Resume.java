package com.simple.resume.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Resume {
    private Integer id;

    private Integer userID;

    private String userName;

    private Integer sex;

    private Date birthday;

    private Integer experience;

    private String nativePlace;

    private String education;

    private String phone;

    private String email;

    private String picture;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Timestamp deliverTime;

    private Integer status;

    private String selfAppraisal;
}