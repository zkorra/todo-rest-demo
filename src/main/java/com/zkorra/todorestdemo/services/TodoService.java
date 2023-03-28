package com.zkorra.todorestdemo.services;

import com.zkorra.todorestdemo.exceptions.BaseException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import com.zkorra.todorestdemo.models.TodoItem;
import com.zkorra.todorestdemo.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoItemRepository repository;

    @Autowired
    public TodoService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> getAllTodos() {
        List<TodoItem> todoList = repository.findAll();
        return todoList;
    }

    public TodoItem saveTodo(TodoItem newTodo) throws BaseException {

        if (newTodo.getDescription() == null) {
            throw new BaseException("Description is undefined");
        }

        TodoItem todo = repository.save(newTodo);
        return todo;
    }

    public TodoItem updateTodo(TodoItem updatedTodo) throws BaseException {
        TodoItem todo = repository.save(updatedTodo);
        return todo;
    }

    public void deleteTodoById(String id) throws NotFoundException {

        if(!repository.existsById(id)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        repository.deleteById(id);

    }

}
