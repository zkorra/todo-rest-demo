package com.zkorra.todorestdemo.domain.todo.repository;

import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@EnableJpaAuditing
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    private UserEntity savedUser;

    private TodoEntity todo1;
    private TodoEntity todo2;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.save(UserEntity.builder().email("user@test.com").password("password").build());

        todo1 = TodoEntity.builder().slug("todo1").task("task1").description("").user(savedUser).build();
        todo2 = TodoEntity.builder().slug("todo2").task("task2").description("").user(savedUser).build();

        todoRepository.saveAll(List.of(todo1, todo2));
    }

    @Test
    void whenThereAreTodos_thenReturnTodoBySlug() {
        Optional<TodoEntity> opt = todoRepository.findBySlug(todo1.getSlug());

        if (opt.isEmpty()) {
            fail("todo not initialized");
        }

        TodoEntity todo = opt.get();

        assertEquals(todo1.getSlug(), todo.getSlug());
    }

    @Test
    void whenThereAreTodos_thenDeleteAllTodosByUser() {
        int deleted = todoRepository.deleteTodosByUserId(savedUser.getId());

        assertEquals(2, deleted);
    }
}
