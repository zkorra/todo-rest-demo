package com.zkorra.todorestdemo.exceptions;

import lombok.Getter;

import java.util.Date;

public class ErrorResponse {

    @Getter
    private int code;

    @Getter
    private Date timestamp;

    @Getter
    private String message;

    public ErrorResponse(int code, Date timestamp, String message) {
        this.code = code;
        this.timestamp = timestamp;
        this.message = message;
    }
}
