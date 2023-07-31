package com.zkorra.todorestdemo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private String email;
    private String token;
    private String displayName;

    @Getter
    @AllArgsConstructor
    public static class Registration {
        private String email;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }
}
