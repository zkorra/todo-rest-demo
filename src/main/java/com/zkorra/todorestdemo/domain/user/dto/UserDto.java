package com.zkorra.todorestdemo.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
        @NotEmpty
        @Email
        private String email;

        @NotNull
        @NotEmpty
        @Size(min = 8, max = 64)
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Login {

        @NotNull
        @NotEmpty
        @Email
        private String email;

        @NotNull
        @NotEmpty
        @Size(min = 8, max = 64)
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Email
        private String email;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Size(min = 8, max = 64)
        private String password;

        private String displayName;
    }
}
