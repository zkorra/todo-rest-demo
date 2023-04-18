package com.zkorra.todorestdemo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String email;
    private String token;

    @Getter
    @AllArgsConstructor
    public static class Registration {
        private String email;
        private String password;
        private String displayName;
    }

    @Getter
    @AllArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }
}
