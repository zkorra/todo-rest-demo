package com.zkorra.todorestdemo.domain.todoItem.controller;

import com.zkorra.todorestdemo.domain.todoItem.dto.TodoDTO;
import com.zkorra.todorestdemo.domain.todoItem.dto.TodoPostRequestDTO;
import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItem;
import com.zkorra.todorestdemo.domain.todoItem.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Iterable<TodoItem> todoList = todoService.getAllTodos();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody TodoPostRequestDTO todoDTO) {
        TodoItem savedTodo = todoService.saveTodo(todoDTO);
        TodoDTO savedTodoDTO = new TodoDTO(savedTodo);

        return new ResponseEntity<>(savedTodoDTO, HttpStatus.CREATED);
    }

//    @PutMapping
//    public ResponseEntity<?> updateTodo(@RequestBody TodoPutRequestDTO todoDTO) {
//        TodoItem updatedTodo = todoService.updateTodo(todoDTO);
//        TodoDTO updatedTodoDTO = new TodoDTO(updatedTodo);
//
//        return new ResponseEntity<>(updatedTodoDTO, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}