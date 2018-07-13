package com.simple.resume.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 简历的基本信息类
 */
@Data
public class Resume {
    private Integer id;

    private Integer userID;

    private String userName;

    private Integer sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    private String experience;

    private String nativePlace;

    private String education;

    private String phone;

    private String email;

    private String picture;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Timestamp updateTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Timestamp deliverTime;

    private Integer status;

    private String selfAppraisal;

}