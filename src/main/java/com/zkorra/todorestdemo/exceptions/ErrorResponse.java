package com.zkorra.todorestdemo.exceptions;

import lombok.Getter;

import java.util.Date;

public class ErrorResponse {

    @Getter
    private int statusCode;

    @Getter
    private Date timestamp;

    @Getter
    private String message;

    public ErrorResponse(int statusCode, Date timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }
}
