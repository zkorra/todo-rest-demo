package com.zkorra.todorestdemo.domain.user.entity;

import com.zkorra.todorestdemo.domain.common.entity.BaseEntity;
import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "display_name")
    private String displayName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEntity> todoList;

    @Builder
    public UserEntity(String id, String email, String password, String displayName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }
}
