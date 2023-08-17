package com.zkorra.todorestdemo.domain.user.dto;

public class UserDto {
    private String email;
    private String token;
    private String displayName;

    public UserDto() {
    }

    public UserDto(String email, String token, String displayName) {
        this.email = email;
        this.token = token;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
