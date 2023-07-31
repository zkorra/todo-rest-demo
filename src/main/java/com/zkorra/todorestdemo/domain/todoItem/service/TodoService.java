package com.zkorra.todorestdemo.domain.todoItem.service;

import com.zkorra.todorestdemo.domain.todoItem.dto.TodoDto;
import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItemEntity;
import com.zkorra.todorestdemo.domain.todoItem.repository.TodoItemRepository;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.exceptions.BaseException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoItemRepository repository;
    private final Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    public TodoService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public List<TodoDto> getAllTodos() throws BaseException {
        List<TodoItemEntity> todoItems = repository.findAll();
        return todoItems.stream()
                .map(item -> TodoDto.builder()
                        .task(item.getTask())
                        .description(item.getDescription())
                        .completed(item.isCompleted())
                        .updatedAt(item.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public TodoDto saveTodo(TodoDto.Request todo, AuthUserDetails authUserDetails) {

        if (todo.getTask() == null || todo.getTask().isEmpty()) {
            throw new BaseException("Task is undefined");
        }

        UserEntity user = UserEntity.builder().id(authUserDetails.getId()).build();

        TodoItemEntity newTodo = TodoItemEntity.builder()
                .task(todo.getTask())
                .description(todo.getDescription())
                .completed(false)
                .user(user)
                .build();


        repository.save(newTodo);

        return TodoDto.builder()
                .task(newTodo.getTask())
                .description(newTodo.getDescription())
                .completed(newTodo.isCompleted())
                .updatedAt(newTodo.getUpdatedAt())
                .build();
    }

    public void deleteTodoById(String id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        repository.deleteById(id);
    }

}
