package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder, jwtUtils);
    }

//    @Test
//    void whenDeleteUser_thenDeleteTodoOwnedByUserAndUser() {
//        UserEntity user = UserEntity.builder().id("test-id").email("test@test.com").password("encodedPassword").build();
//
//        TodoEntity todo1 = TodoEntity.builder().id("todo-id1").task("task1").user(user).build();
//        TodoEntity todo2 = TodoEntity.builder().id("todo-id2").task("task2").user(user).build();
//
//        todoRepository.save(todo1);
//        todoRepository.save(todo2);
//
//        int todoCount = todoRepository.findAll().size();
//
//        int deletedCount = todoRepository.deleteTodosByUserId(user.getId());
//
//        assertEquals(todoCount, 2);
//
//        assertEquals(deletedCount, 2);
//
//        verify(todoRepository times(2)).save(any(TodoEntity.class));
//        verify(todoRepository).delete(any(TodoEntity.class));
//    }

    @Test
    void whenDeleteUser_thenDeleteTodosAndUser() {

    }
}
