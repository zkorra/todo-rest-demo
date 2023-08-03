package com.zkorra.todorestdemo.exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {

    private final int code;
    private final Date timestamp;
    private final String message;

    public ErrorResponse(int code, Date timestamp, String message) {
        this.code = code;
        this.timestamp = timestamp;
        this.message = message;
    }
}
