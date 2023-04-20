package com.zkorra.todorestdemo.domain.todoItem.dto;

import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TodoDto {


    private String task;


    private String description;

    private Date timestamp;

}
