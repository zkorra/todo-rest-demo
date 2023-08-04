package com.zkorra.todorestdemo.domain.user.controller;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.service.UserService;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping()
    public ResponseEntity<UserDto> currentUser(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return ResponseEntity.ok(userService.currentUser(authUserDetails));
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto.Update updateInfo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return ResponseEntity.ok(userService.updateUser(updateInfo, authUserDetails));
    }
}
