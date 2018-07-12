package com.simple.resume.VO;

import com.simple.resume.pojo.Skill;
import com.simple.resume.pojo.Workexperience;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResumeVO implements Serializable {
    private String userName;

    private String s_sex;

    private String birthday;

    private String experience;

    private String phone;

    private String email;

    private String education;

    private String nativePlace;

    private Integer status;

    private String startTime;

    private String endTime;

    private String school;

    private String major;

    private String description;

    private String position;

    private String salary;

    private String ondutytime;

    private String w_workstyle;

    List<Workexperience> workexperiences = new ArrayList<>();

    List<Skill> skills = new ArrayList<>();

    private String selfAppraisal;


}
