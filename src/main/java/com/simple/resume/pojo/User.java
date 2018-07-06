package com.simple.resume.pojo;

import lombok.Data;

@Data
public class User {
    private int userID;
    private String userName;
    private String password;
    private int sex;
    private String phone;
    private String email;
}
