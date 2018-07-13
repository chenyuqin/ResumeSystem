package com.simple.resume.pojo;

import lombok.Data;

/**
 * 用于全文检索的类，info字段只是把一个用户的所有简历信息序列化之后存入了数据库中，再进行模糊查询
 * TODO 或许可以用solr解决，分词器
 */
@Data
public class ResumeText {
    private Integer id;

    private Integer resumeId;

    private Integer userID;

    private String info;
}
