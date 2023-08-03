package com.zkorra.todorestdemo.domain.todo.repository;

import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
}
