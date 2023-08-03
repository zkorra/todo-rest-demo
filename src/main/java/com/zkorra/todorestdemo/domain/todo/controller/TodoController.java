package com.zkorra.todorestdemo.domain.todo.controller;

import com.zkorra.todorestdemo.domain.todo.dto.TodoDto;
import com.zkorra.todorestdemo.domain.todo.service.TodoService;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoList = todoService.getAllTodos();
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<TodoDto> getTodoBySlug(@PathVariable String slug) {
        TodoDto todoDto = todoService.getTodoBySlug(slug);
        return ResponseEntity.ok(todoDto);
    }

    @PostMapping
    public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto.Request todoDTO, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return ResponseEntity.ok(todoService.saveTodo(todoDTO, authUserDetails));
    }

//    @PutMapping
//    public ResponseEntity<?> updateTodo(@RequestBody TodoPutRequestDTO todoDTO) {
//        TodoItem updatedTodo = todoService.updateTodo(todoDTO);
//        TodoDTO updatedTodoDTO = new TodoDTO(updatedTodo);
//
//        return new ResponseEntity<>(updatedTodoDTO, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable String id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
