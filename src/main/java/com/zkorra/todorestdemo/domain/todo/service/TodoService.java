package com.zkorra.todorestdemo.domain.todo.service;

import com.zkorra.todorestdemo.domain.todo.dto.TodoDto;
import com.zkorra.todorestdemo.domain.todo.dto.TodoRequestDto;
import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import com.zkorra.todorestdemo.domain.todo.repository.TodoRepository;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.exception.InvalidInputException;
import com.zkorra.todorestdemo.exception.ResourceNotFoundException;
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
                .map(item -> new TodoDto(item.getSlug(), item.getTask(), item.getDescription(), item.isCompleted(), item.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public TodoDto getTodoBySlug(String slug) {

        Optional<TodoEntity> opt = todoRepository.findBySlug(slug);

        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("todo not found");
        }

        TodoEntity todoItem = opt.get();

        return new TodoDto(todoItem.getSlug(), todoItem.getTask(), todoItem.getDescription(), todoItem.isCompleted(), todoItem.getUpdatedAt());
    }

    public TodoDto saveTodo(TodoRequestDto todo, AuthUserDetails authUserDetails) {

        if (todo.getTask() == null || todo.getTask().isEmpty()) {
            throw new InvalidInputException("todo information is invalid");
        }

        UserEntity user = new UserEntity();
        user.setId(authUserDetails.getId());

        String slug = randomSlug();

        TodoEntity newTodo = new TodoEntity(slug, todo.getTask(), todo.getDescription(), false, user);

        todoRepository.save(newTodo);

        return new TodoDto(newTodo.getSlug(), newTodo.getTask(), newTodo.getDescription(), newTodo.isCompleted(), newTodo.getUpdatedAt());
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
