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
        savedUser = userRepository.save(new UserEntity("user@test.com", "password", ""));
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void whenDeleteValidUser_thenDeleteUserTodos() {

        TodoEntity todo1 = new TodoEntity("slug1", "task1", "desc1", false, savedUser);
        TodoEntity todo2 = new TodoEntity("slug2", "task2", "desc2", false, savedUser);

        todoRepository.saveAll(List.of(todo1, todo2));

        assertEquals(2, todoRepository.count());

        userRepository.deleteById(savedUser.getId());

        assertEquals(0, todoRepository.count());
        assertEquals(0, userRepository.count());
    }
}
