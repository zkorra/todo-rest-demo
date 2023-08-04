package com.zkorra.todorestdemo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private String email;
    private String token;
    private String displayName;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Registration {

        @NotNull
        @Email
        private String email;

        @NotNull
        @Size(min = 8, max = 64)
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Login {

        @NotNull
        @Email
        private String email;

        @NotNull
        @Size(min = 8, max = 64)
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        private String email;
        private String password;
        private String displayName;
    }
}
