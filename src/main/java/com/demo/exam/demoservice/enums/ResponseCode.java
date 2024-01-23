package com.demo.exam.demoservice.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("0000", "成功"),
    INPUT_DURATION_INVALID("E001", "日期區間不符"),
    ;

    private final String code;
    private final String message;


    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
