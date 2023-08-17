package com.zkorra.todorestdemo.domain.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginDto {
    @NotEmpty
    @Size(min = 6, max = 64)
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
