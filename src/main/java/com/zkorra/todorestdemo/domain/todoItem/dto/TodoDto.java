package com.zkorra.todorestdemo.domain.todoItem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoDto {
    private String task;
    private String description;
}
