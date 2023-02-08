package com.zkorra.todorestdemo.data.repositories;

import com.zkorra.todorestdemo.data.entities.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class TodoItemRepoMock {

    /**
     * A list to represent our database
     */
    private List<TodoItem> repository = new ArrayList<>();

    /**
     * Default constructor
     */
    public TodoItemRepoMock() {
    }

    public TodoItem save(TodoItem newTodo) {
        int index = -99;

        // Check if the newToDo has an ID, if so check in the repository for
        // an existing entity with the same ID
        if (newTodo.getId() != null) {
            index = findIdInRepo(newTodo.getId());
        }

        // If we found an existing entity with the same ID update it instead
        // of creating a new one
        if (index != -99) {
            repository.get(index).setCompleted(newTodo.isCompleted());
            repository.get(index).setDescription(newTodo.getDescription());
            return repository.get(index);
        } else {
            Integer id = repository.size() + 1;
            newTodo.setId(id.toString());
            repository.add(newTodo);
            return newTodo;
        }
    }

    /**
     * Returns the list of all entities from the mock database
     *
     * @return - all saved entities
     */
    public List<TodoItem> findAll() {
        return repository;
    }

    /**
     * Deletes the entity with the given ID
     *
     * @param id - the ID of the entity to be deleted
     * @throws IllegalArgumentException - when the given ID doesn't exist
     */
    public void deleteById(String id) {
        int index = findIdInRepo(id);


        if (index != -99) {
            repository.remove(index);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Check in the repository for an entity with the given ID
     */
    public int findIdInRepo(String id) {
        for (int i = 0; i <= repository.size(); i++) {
            if (repository.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -99;
    }
}
