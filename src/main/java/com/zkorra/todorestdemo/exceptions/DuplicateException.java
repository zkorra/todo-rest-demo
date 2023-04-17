package com.zkorra.todorestdemo.exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}
