package com.zkorra.todorestdemo.domain.todo.entity;

import com.zkorra.todorestdemo.domain.common.entity.BaseEntity;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class TodoEntity extends BaseEntity {

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String task;

    @Column()
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Builder
    public TodoEntity(String id, String slug, String task, String description, boolean completed, UserEntity user) {
        this.id = id;
        this.slug = slug;
        this.task = task;
        this.description = description;
        this.completed = completed;
        this.user = user;
    }
}
