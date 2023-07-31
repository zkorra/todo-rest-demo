package com.zkorra.todorestdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.EXPECTATION_FAILED.value(), new Date(), e.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), new Date(), e.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
    }
}
