package com.zkorra.todorestdemo.domain.todoItem.entity;

import com.zkorra.todorestdemo.domain.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "todo_items")
public class TodoItem extends BaseEntity {

    @Column(nullable = false)
    private String task;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private Date timestamp;

//    public TodoItem(String id, String task, String description, boolean completed, Date timestamp) {
//        this.id = id;
//        this.task = task;
//        this.description = description;
//        this.completed = completed;
//        this.timestamp = timestamp;
//    }
}
