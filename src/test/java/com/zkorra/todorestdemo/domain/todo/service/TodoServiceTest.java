package com.zkorra.todorestdemo.domain.todo.service;


import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import com.zkorra.todorestdemo.domain.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    void whenValidTodoCreation_thenReturnTodoDto() {
//        TodoDto.Request todo = TodoDto.Request.builder().task("todo1").description("description").build();

//        UserDto actual = todoService.saveTodo(todo);

        verify(todoRepository, times(1)).save(any(TodoEntity.class));
    }
}
