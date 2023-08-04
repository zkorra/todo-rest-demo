package com.zkorra.todorestdemo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

        @NotEmpty
        @Size(min = 6, max = 64)
        @Email
        private String email;

        @NotEmpty
        @Size(min = 8, max = 64)
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Login {

        @NotEmpty
        @Size(min = 6, max = 64)
        @Email
        private String email;

        @NotEmpty
        @Size(min = 8, max = 64)
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {

        @Size(min = 6, max = 64)
        @Email
        private String email;

        @Size(min = 8, max = 64)
        private String password;

        @Size(max = 64)
        private String displayName;
    }
}
