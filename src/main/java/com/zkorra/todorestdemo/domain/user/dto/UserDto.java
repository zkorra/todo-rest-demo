package com.zkorra.todorestdemo.domain.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {

    public UserDto(String email, String token, String displayName) {
        this.email = email;
        this.token = token;
        this.displayName = displayName;
    }

    private String email;
    private String token;
    private String displayName;

    public String getEmail() {
        return this.email;
    }

    public String getToken() {
        return this.token;
    }

    public String getDisplayName() {
        return this.displayName;
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

    public static class Registration {

        @NotEmpty
        @Size(min = 6, max = 64)
        @Email
        private String email;

        @NotEmpty
        @Size(min = 8, max = 64)
        private String password;

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }
    }

    public static class Login {

        @NotEmpty
        @Size(min = 6, max = 64)
        @Email
        private String email;

        @NotEmpty
        @Size(min = 8, max = 64)
        private String password;

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }
    }

    public static class Update {

        @Size(min = 6, max = 64)
        @Email
        private String email;

        @Size(min = 8, max = 64)
        private String password;

        @Size(max = 64)
        private String displayName;

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }

        public String getDisplayName() {
            return this.displayName;
        }
    }
}
