package com.simple.resume.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户类
 */
@Data
public class User implements Serializable {
    private Integer userID;

    private String userName;

    private String password;

    private Integer sex;

    private String phone;

    private String email;

    private String activeCode;

    private Integer activeStatus;

    private Integer isLogined;

    private Integer permission;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Timestamp activeTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Timestamp updateTime;

    private String description;

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", activeCode='" + activeCode + '\'' +
                ", activeStatus=" + activeStatus +
                ", isLogined=" + isLogined +
                ", createTime=" + createTime +
                ", activeTime=" + activeTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
