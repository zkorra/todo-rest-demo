package com.zkorra.todorestdemo.domain.todoItem.dto;

import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItem;
import lombok.Getter;
import lombok.Setter;

public class TodoPostRequestDTO {
    @Getter
    @Setter
    private String task;

    @Getter
    @Setter
    private String description;

    public TodoPostRequestDTO() {
    }

    public TodoPostRequestDTO(TodoItem todo) {
        this.task = todo.getTask();
        this.description = todo.getDescription();
    }
}
