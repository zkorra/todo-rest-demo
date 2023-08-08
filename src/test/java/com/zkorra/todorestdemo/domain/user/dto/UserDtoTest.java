package com.zkorra.todorestdemo.domain.user.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDtoTest {

    @Autowired
    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void whenEmptyEmailOrPasswordRegistration_thenValidationFail() {
        UserDto.Registration user = UserDto.Registration.builder().email("").password("").build();
        Set<ConstraintViolation<UserDto.Registration>> violations = this.validator.validate(user);

        boolean invalidEmail = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath()
                        .toString()
                        .equals("email") && violation.getInvalidValue().toString().isEmpty());

        boolean invalidPassword = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath()
                        .toString()
                        .equals("password") && violation.getInvalidValue().toString().isEmpty());

        assertTrue(invalidEmail);
        assertTrue(invalidPassword);
    }

    @Test
    void whenNullEmailOrPasswordRegistration_thenValidationFail() {
        UserDto.Registration user = UserDto.Registration.builder().build();
        Set<ConstraintViolation<UserDto.Registration>> violations = this.validator.validate(user);

        boolean invalidEmail = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath()
                        .toString()
                        .equals("email") && violation.getInvalidValue() == null);

        boolean invalidPassword = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath()
                        .toString()
                        .equals("password") && violation.getInvalidValue() == null);

        assertTrue(invalidEmail);
        assertTrue(invalidPassword);
    }

    @Test
    void whenInvalidEmail_thenValidationFail() {
        UserDto.Registration user = UserDto.Registration.builder().email("my-email.com").build();
        Set<ConstraintViolation<UserDto.Registration>> violations = this.validator.validate(user);

        boolean invalidEmail = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath()
                        .toString()
                        .equals("email") && violation.getInvalidValue().toString().equals(user.getEmail()));

        assertTrue(invalidEmail);
    }


}
