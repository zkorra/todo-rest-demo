package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
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

    public UserDto register(UserDto.Registration registration) {

        Optional<UserEntity> opt = userRepository.findByEmail(registration.getEmail());

        if (opt.isPresent()) {
            logger.error("{} is duplicated", opt.get().getEmail());
            throw new ResourceConflictException("there is duplicated user information");
        }

        String encodedPassword = passwordEncoder.encode(registration.getPassword());

        UserEntity user = UserEntity.builder()
                .email(registration.getEmail())
                .password(encodedPassword)
                .displayName("")
                .build();

        userRepository.save(user);
        logger.info("{} registered successfully", user.getEmail());

        return UserDto.builder().email(user.getEmail()).displayName(user.getDisplayName()).build();
    }

    public UserDto login(UserDto.Login login) {

        Optional<UserEntity> opt = userRepository.findByEmail(login.getEmail());

        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }

        UserEntity user = opt.get();

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidInputException("password is incorrect");
        }

        String jwtToken = jwtUtils.generateToken(user);

        return UserDto.builder().email(user.getEmail()).token(jwtToken).displayName(user.getDisplayName()).build();
    }
}
