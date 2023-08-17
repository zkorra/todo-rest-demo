package com.zkorra.todorestdemo.domain.todo.entity;

import com.zkorra.todorestdemo.domain.common.entity.BaseEntity;
import com.zkorra.todorestdemo.domain.tag.entity.TagEntity;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todos")
public class TodoEntity extends BaseEntity {

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String task;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagEntity> tagList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public TodoEntity() {
    }

    public TodoEntity(String slug, String task, String description, boolean completed, UserEntity user) {
        this.slug = slug;
        this.task = task;
        this.description = description;
        this.completed = completed;
        this.user = user;
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
}
