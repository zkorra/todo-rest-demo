package com.zkorra.todorestdemo.domain.todo.service;

import com.zkorra.todorestdemo.domain.todo.dto.TodoDto;
import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import com.zkorra.todorestdemo.domain.todo.repository.TodoRepository;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.exceptions.InvalidInputException;
import com.zkorra.todorestdemo.exceptions.ResourceNotFoundException;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDto> getAllTodos() {
        List<TodoEntity> todoItems = todoRepository.findAll();
        return todoItems.stream()
                .map(item -> TodoDto.builder()
                        .slug(item.getSlug())
                        .task(item.getTask())
                        .description(item.getDescription())
                        .completed(item.isCompleted())
                        .updatedAt(item.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public TodoDto getTodoBySlug(String slug) {

        Optional<TodoEntity> opt = todoRepository.findBySlug(slug);

        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("todo not found");
        }

        TodoEntity todoItem = opt.get();

        return TodoDto.builder()
                .slug(todoItem.getSlug())
                .task(todoItem.getTask())
                .description(todoItem.getDescription())
                .completed(todoItem.isCompleted())
                .updatedAt(todoItem.getUpdatedAt())
                .build();
    }

    public TodoDto saveTodo(TodoDto.Request todo, AuthUserDetails authUserDetails) {

        if (todo.getTask() == null || todo.getTask().isEmpty()) {
            throw new InvalidInputException("todo information is invalid");
        }

        UserEntity user = UserEntity.builder().id(authUserDetails.getId()).build();

        String slug = randomSlug();

        TodoEntity newTodo = TodoEntity.builder()
                .slug(slug)
                .task(todo.getTask())
                .description(todo.getDescription())
                .completed(false)
                .user(user)
                .build();

        todoRepository.save(newTodo);

        return TodoDto.builder()
                .slug(newTodo.getSlug())
                .task(newTodo.getTask())
                .description(newTodo.getDescription())
                .completed(newTodo.isCompleted())
                .updatedAt(newTodo.getUpdatedAt())
                .build();
    }

    public void deleteTodoById(String id) {

        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("todo not found");
        }

        todoRepository.deleteById(id);
    }

    /* TODO: Make randomSlug() more user-friendly readable */
    private static String randomSlug() {
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890";

        Random rand = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int index = rand.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

}
