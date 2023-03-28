package com.zkorra.todorestdemo.controllers;

import com.zkorra.todorestdemo.models.TodoItem;
import com.zkorra.todorestdemo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTodos() {
        List<TodoItem> todoList = todoService.getAllTodos();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody TodoItem newTodo) {
        TodoItem persistedTodo = todoService.saveTodo(newTodo);
        return new ResponseEntity<>(persistedTodo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoItem updatedTodo) {
        TodoItem persistedTodo = todoService.updateTodo(updatedTodo);
        return new ResponseEntity<>(persistedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable String id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
