package com.zkorra.todorestdemo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TodoItem {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean completed;

    public TodoItem() {
    }

    public TodoItem(String description, boolean completed) {
        setDescription(description);
        setCompleted(completed);
    }
}
