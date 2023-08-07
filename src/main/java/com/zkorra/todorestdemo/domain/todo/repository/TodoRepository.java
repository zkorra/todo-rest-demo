package com.zkorra.todorestdemo.domain.todo.repository;

import com.zkorra.todorestdemo.domain.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    Optional<TodoEntity> findBySlug(String slug);

    @Modifying
    @Query("delete from TodoEntity t where t.user.id=:userId")
    int deleteTodosByUserId(@Param("userId") String userId);
}
