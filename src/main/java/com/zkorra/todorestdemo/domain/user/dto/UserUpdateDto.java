package com.zkorra.todorestdemo.domain.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserUpdateDto {
    @Size(min = 6, max = 64)
    @Email
    private String email;

    @Size(min = 8, max = 64)
    private String password;

    @Size(max = 64)
    private String displayName;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }
}
