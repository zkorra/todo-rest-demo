package com.zkorra.todorestdemo.domain.tag.entity;

import com.zkorra.todorestdemo.domain.common.entity.BaseEntity;
import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class TagEntity extends BaseEntity {

    @Column(nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private TodoEntity todo;

    @Builder
    public TagEntity(String id, String tag, TodoEntity todo) {
        this.id = id;
        this.tag = tag;
        this.todo = todo;
    }
}
