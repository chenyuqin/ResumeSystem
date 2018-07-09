package com.simple.resume.common;

public enum StatusEnum {
    SUCCESS(0),
    ERROR(1),
    NOTACTIVE(2),
    AlreadyLogined(3),
    EmailNotExist(4);


    int code;
    StatusEnum(int i) {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
