package com.zkorra.todorestdemo.domain.user.repository;

import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import com.zkorra.todorestdemo.domain.todo.repository.TodoRepository;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    private UserEntity savedUser;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.save(UserEntity.builder().email("user@test.com").password("password").build());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void whenDeleteValidUser_thenDeleteUserTodos() {

        TodoEntity todo1 = TodoEntity.builder().slug("todo1").task("task1").user(savedUser).build();
        TodoEntity todo2 = TodoEntity.builder().slug("todo2").task("task2").user(savedUser).build();

        todoRepository.saveAll(List.of(todo1, todo2));

        assertEquals(2, todoRepository.count());

        userRepository.deleteById(savedUser.getId());

        assertEquals(0, todoRepository.count());
        assertEquals(0, userRepository.count());
    }
}
