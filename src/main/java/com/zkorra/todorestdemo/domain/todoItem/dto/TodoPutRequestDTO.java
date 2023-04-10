package com.zkorra.todorestdemo.domain.todoItem.dto;

import lombok.Getter;
import lombok.Setter;

public class TodoPutRequestDTO {

    @Getter
    @Setter
    private String task;

    @Getter
    @Setter
    private String description;

    public TodoPutRequestDTO() {
    }

//    public TodoPutRequestDTO(TodoItem todo) {
//        this.task = todo.getTask();
//        this.description = todo.getDescription();
//    }
}
