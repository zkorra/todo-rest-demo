package com.zkorra.todorestdemo.domain.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TodoDto {
    private String slug;
    private String task;
    private String description;
    private boolean completed;
    private LocalDateTime updatedAt;

    @Getter
    @AllArgsConstructor
    public static class Request {
        private String task;
        private String description;
    }
}

