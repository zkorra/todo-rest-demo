package com.zkorra.todorestdemo.domain.todoItem.service;

import com.zkorra.todorestdemo.domain.todoItem.dto.TodoDto;
import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItemEntity;
import com.zkorra.todorestdemo.domain.todoItem.repository.TodoItemRepository;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.exceptions.BaseException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TodoService {

    private final TodoItemRepository repository;

    @Autowired
    public TodoService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public Iterable<TodoItemEntity> getAllTodos() {
        return repository.findAll();
    }

    public TodoDto saveTodo(TodoDto todoDTO, AuthUserDetails authUserDetails) throws BaseException {
        String todoTask = todoDTO.getTask();
        String todoDescription = todoDTO.getDescription();

        if (todoTask == null || todoTask.isEmpty()) {
            throw new BaseException("Task is undefined");
        }

        UserEntity user = new UserEntity(authUserDetails.getId());

        TodoItemEntity newTodo = new TodoItemEntity(todoTask, todoDescription, false, new Date(), user);

        repository.save(newTodo);

        return new TodoDto(newTodo.getTask(), newTodo.getDescription());
    }

    public void deleteTodoById(String id) throws NotFoundException {

        if (!repository.existsById(id)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        repository.deleteById(id);
    }

}
