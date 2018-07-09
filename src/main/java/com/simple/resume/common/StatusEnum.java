package com.simple.resume.common;

import java.io.Serializable;

public enum StatusEnum implements Serializable {
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
