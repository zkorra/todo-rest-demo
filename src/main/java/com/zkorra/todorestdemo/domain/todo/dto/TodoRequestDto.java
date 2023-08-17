package com.zkorra.todorestdemo.domain.todo.dto;

public class TodoRequestDto {
    private String task;
    private String description;

    public TodoRequestDto() {
    }

    public TodoRequestDto(String task, String description) {
        this.task = task;
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

}
