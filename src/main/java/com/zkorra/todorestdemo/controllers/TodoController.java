package com.zkorra.todorestdemo.controllers;

import com.zkorra.todorestdemo.models.TodoItem;
import com.zkorra.todorestdemo.repositories.TodoItemRepoMock;
import com.zkorra.todorestdemo.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoItemRepository repository;

    @Autowired
    public TodoController(TodoItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> getAllTodos() {
        List<TodoItem> todoList = repository.findAll();
        return new ResponseEntity(todoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody TodoItem newTodo) {
        TodoItem todo = repository.save(newTodo);
        return new ResponseEntity(todo, HttpStatus.CREATED);
    }
}
