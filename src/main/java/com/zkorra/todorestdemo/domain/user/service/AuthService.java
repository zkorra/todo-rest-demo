package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.dto.UserLoginDto;
import com.zkorra.todorestdemo.domain.user.dto.UserRegistrationDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exception.InvalidInputException;
import com.zkorra.todorestdemo.exception.ResourceConflictException;
import com.zkorra.todorestdemo.exception.ResourceNotFoundException;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public UserDto register(UserRegistrationDto registration) {

        Optional<UserEntity> opt = userRepository.findByEmail(registration.getEmail());

        if (opt.isPresent()) {
            logger.error("{} is duplicated", opt.get().getEmail());
            throw new ResourceConflictException("there is duplicated user information");
        }

        String encodedPassword = passwordEncoder.encode(registration.getPassword());

        UserEntity user = new UserEntity(registration.getEmail(), encodedPassword, "");

        userRepository.save(user);
        logger.info("{} registered successfully", user.getEmail());

        return new UserDto(user.getEmail(), "", user.getDisplayName());
    }

    public UserDto login(UserLoginDto login) {

        Optional<UserEntity> opt = userRepository.findByEmail(login.getEmail());

        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }

        UserEntity user = opt.get();

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidInputException("email or password is incorrect");
        }

        String jwtToken = jwtUtils.generateToken(user);

        return new UserDto(user.getEmail(), jwtToken, user.getDisplayName());
    }
}
