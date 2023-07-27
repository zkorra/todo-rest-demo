package com.zkorra.todorestdemo.domain.todoItem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TodoDto {
    private String task;
    private String description;
    private boolean completed;
    private Date timestamp;

    @Getter
    @AllArgsConstructor
    public static class Request {
        private String task;
        private String description;
    }
}

