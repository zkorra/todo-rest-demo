package com.zkorra.todorestdemo.domain.todoItem.entity;

import com.zkorra.todorestdemo.domain.common.entity.BaseEntity;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo_items")
public class TodoItemEntity extends BaseEntity {

    @Column(nullable = false)
    private String task;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
