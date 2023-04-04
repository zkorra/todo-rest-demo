package com.zkorra.todorestdemo.dtos;

import com.zkorra.todorestdemo.models.TodoItem;
import lombok.Getter;
import lombok.Setter;

public class TodoPostRequestDTO {
    @Getter
    @Setter
    private String task;

    @Getter
    @Setter
    private String description;

    public TodoPostRequestDTO() {}

    public TodoPostRequestDTO(TodoItem todo) {
        this.task = todo.getTask();
        this.description = todo.getDescription();
    }
}
