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
        return repository.findAll();
    }

    public TodoItem saveTodo(TodoItem newTodo) throws BaseException {
        String todoTask = newTodo.getTask();

        if (todoTask == null || todoTask.isEmpty()) {
            throw new BaseException("Task is undefined");
        }

        newTodo.setId(null);
        newTodo.setCompleted(false);

        return repository.save(newTodo);
    }

    public TodoItem updateTodo(TodoItem updatedTodo) throws BaseException {
        String todoId = updatedTodo.getId();
        String todoTask = updatedTodo.getTask();

        if (todoId == null || todoId.isEmpty()) {
            throw new BaseException("Todo ID is undefined");
        }

        if(!repository.existsById(todoId)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        if (todoTask == null || todoTask.isEmpty()) {
            throw new BaseException("Task is undefined");
        }

        return repository.save(updatedTodo);
    }

    public void deleteTodoById(String id) throws NotFoundException {

        if(!repository.existsById(id)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        repository.deleteById(id);
    }

}
