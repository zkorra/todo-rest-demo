package com.zkorra.todorestdemo.domain.user.controller;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto.Registration registration) {
        return ResponseEntity.ok(userService.register(registration));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto.Login login) {
        return ResponseEntity.ok(userService.login(login));
    }
}
