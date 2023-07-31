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

import java.util.Date;
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
        return todoItems.stream().map(item -> new TodoDto(item.getTask(), item.getDescription(), item.isCompleted(), item.getTimestamp())).collect(Collectors.toList());
    }

    public TodoDto saveTodo(TodoDto.Request todoDTO, AuthUserDetails authUserDetails) {
        String todoTask = todoDTO.getTask();
        String todoDescription = todoDTO.getDescription();

        if (todoTask == null || todoTask.isEmpty()) {
            throw new BaseException("Task is undefined");
        }

        UserEntity user = new UserEntity(authUserDetails.getId());

        TodoItemEntity newTodo = new TodoItemEntity(todoTask, todoDescription, false, new Date(), user);

        repository.save(newTodo);

        return new TodoDto(newTodo.getTask(), newTodo.getDescription(), newTodo.isCompleted(), newTodo.getTimestamp());
    }

    public void deleteTodoById(String id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        repository.deleteById(id);
    }

}
