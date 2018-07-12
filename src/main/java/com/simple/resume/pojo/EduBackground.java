package com.simple.resume.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EduBackground {
    private Integer id;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;

    private String school;

    private String major;

    private String description;

    private Integer userID;

    private Integer resumeId;

    @Override
    public String toString() {
        return "EduBackground{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", description='" + description + '\'' +
                ", userid=" + userID +
                ", resumeid=" + resumeId +
                '}';
    }
}