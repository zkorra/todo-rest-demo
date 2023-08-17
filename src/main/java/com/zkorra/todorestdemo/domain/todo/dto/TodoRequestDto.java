package com.zkorra.todorestdemo.domain.todo.dto;

public class TodoRequestDto {
    private String task;
    private String description;

    public TodoRequestDto() {
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

}
