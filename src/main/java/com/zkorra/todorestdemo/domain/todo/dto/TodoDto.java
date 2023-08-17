package com.zkorra.todorestdemo.domain.todo.dto;

import java.time.LocalDateTime;

public class TodoDto {

    private String slug;
    private String task;
    private String description;
    private boolean completed;
    private LocalDateTime updatedAt;

    public TodoDto(String slug, String task, String description, boolean completed, LocalDateTime updatedAt) {
        this.slug = slug;
        this.task = task;
        this.description = description;
        this.completed = completed;
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

