package com.zkorra.todorestdemo.dtos;

import com.zkorra.todorestdemo.models.TodoItem;
import lombok.Getter;
import lombok.Setter;

public class TodoPutRequestDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String task;

    @Getter
    @Setter
    private String description;

    public TodoPutRequestDTO() {}

    public TodoPutRequestDTO(TodoItem todo) {
        this.id = todo.getId();
        this.task = todo.getTask();
        this.description = todo.getDescription();
    }
}
