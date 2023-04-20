package com.zkorra.todorestdemo.domain.todoItem.repository;

import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItemEntity, String> {
}
