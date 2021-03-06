package com.simple.resume.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 求职意向类，与简历一对一
 */
@Data
public class Objective {
    private Integer id;

    private String position;

    private String salary;

    private String ondutytime;

    private Integer workstyle;

    private Integer userID;

    private Integer resumeId;

    @Override
    public String toString() {
        return "Objective{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", ondutytime='" + ondutytime + '\'' +
                ", workstyle=" + workstyle +
                ", userid=" + userID +
                ", resumeid=" + resumeId +
                '}';
    }
}