package com.simple.resume.VO;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {
    private Integer userID;

    private String userName;

    private Integer sex;

    private String phone;

    private String email;

    private Date createTime;

    private Integer activeStatus;

    //0表示未投递，1表示已投递
    private Integer isDeliver;

    private String position;

    //简历状态，简历状态，0为未处理，1为approved，2为rejected
    private Integer status;
}
