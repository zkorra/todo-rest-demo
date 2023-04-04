package com.zkorra.todorestdemo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("TodoItem")
public class TodoItem {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String task;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean completed;

    @Getter
    @Setter
    private Date timestamp;
}
