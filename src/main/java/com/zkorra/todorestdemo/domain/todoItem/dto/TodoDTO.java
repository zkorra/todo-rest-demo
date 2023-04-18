package com.zkorra.todorestdemo.domain.todoItem.dto;

import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class TodoDTO {

    @Getter
    @Setter
    private String task;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Date timestamp;

    public TodoDTO() {
    }

    public TodoDTO(TodoItem todo) {
        this.task = todo.getTask();
        this.description = todo.getDescription();
        this.timestamp = todo.getTimestamp();
    }
}