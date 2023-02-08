package com.zkorra.todorestdemo.data.repositories;

import com.zkorra.todorestdemo.data.entities.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * A MongoRepository for the {@link TodoItem} entity to carry out CRUD operations in a
 * MongoDB database
 */
public interface TodoItemRepository extends MongoRepository<TodoItem, String> {

}
