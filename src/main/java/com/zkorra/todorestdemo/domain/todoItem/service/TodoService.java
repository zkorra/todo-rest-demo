package com.zkorra.todorestdemo.domain.todoItem.service;

import com.zkorra.todorestdemo.domain.todoItem.dto.TodoPostRequestDTO;
import com.zkorra.todorestdemo.exceptions.BaseException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import com.zkorra.todorestdemo.domain.todoItem.entity.TodoItem;
import com.zkorra.todorestdemo.domain.todoItem.repository.TodoItemRepository;
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

    public Iterable<TodoItem> getAllTodos() {
        return repository.findAll();
    }

    public TodoItem saveTodo(TodoPostRequestDTO todoDTO) throws BaseException {
        String todoTask = todoDTO.getTask();
        String todoDescription = todoDTO.getDescription();

        if (todoTask == null || todoTask.isEmpty()) {
            throw new BaseException("Task is undefined");
        }

        TodoItem newTodo = new TodoItem();

        newTodo.setId(null);
        newTodo.setTask(todoTask);
        newTodo.setDescription(todoDescription);
        newTodo.setCompleted(false);
        newTodo.setTimestamp(new Date());

        return repository.save(newTodo);
    }

//    public TodoItem updateTodo(TodoPutRequestDTO todoDTO) throws BaseException {
//        String todoId = todoDTO.getId();
//        String todoTask = todoDTO.getTask();
//        String todoDescription = todoDTO.getDescription();
//
//
//        if (todoId == null || todoId.isEmpty()) {
//            throw new BaseException("Todo ID is undefined");
//        }
//
//        if (todoTask == null || todoTask.isEmpty()) {
//            throw new BaseException("Task is undefined");
//        }
//
//        if(!repository.existsById(todoId)) {
//            throw new NotFoundException("Todo doesn't exist");
//        }
//
//        TodoItem updatedTodo = new TodoItem();
//
//        updatedTodo.setId(todoId);
//        updatedTodo.setTask(todoTask);
//        updatedTodo.setDescription(todoDescription);
//        updatedTodo.setTimestamp(new Date());
//
//        return repository.save(updatedTodo);
//    }

    public void deleteTodoById(Long id) throws NotFoundException {

        if(!repository.existsById(id)) {
            throw new NotFoundException("Todo doesn't exist");
        }

        repository.deleteById(id);
    }

}
