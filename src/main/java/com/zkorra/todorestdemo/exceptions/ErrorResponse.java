package com.zkorra.todorestdemo.exceptions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

import java.util.List;

@Getter
@JsonTypeName("error")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class ErrorResponse {

    private final int code;
    private final String message;
    private final List<String> errors;

    public ErrorResponse(int code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

}
