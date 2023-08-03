package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exceptions.ResourceNotFoundException;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public UserDto currentUser(AuthUserDetails authUserDetails) {

        Optional<UserEntity> opt = userRepository.findById(authUserDetails.getId());

        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }

        UserEntity user = opt.get();

        String jwtToken = jwtUtils.generateToken(user);

        return UserDto.builder().email(user.getEmail()).token(jwtToken).displayName(user.getDisplayName()).build();
    }

}
