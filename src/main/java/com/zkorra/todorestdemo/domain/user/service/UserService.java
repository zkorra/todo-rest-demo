package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exception.ResourceConflictException;
import com.zkorra.todorestdemo.exception.ResourceNotFoundException;
import com.zkorra.todorestdemo.security.AuthUserDetails;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public UserDto updateUser(UserDto.Update updateInfo, AuthUserDetails authUserDetails) {

        UserEntity user = userRepository.findById(authUserDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        if (updateInfo.getEmail() != null && !updateInfo.getEmail().equals(user.getEmail())) {

            userRepository.findByEmail(updateInfo.getEmail()).ifPresent((found) -> {
                throw new ResourceConflictException("there is duplicated user email");
            });

            user.setEmail(updateInfo.getEmail());
        }

        if (updateInfo.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updateInfo.getPassword()));
        }

        if (updateInfo.getDisplayName() != null) {
            user.setDisplayName(updateInfo.getDisplayName());
        }

        userRepository.save(user);

        return UserDto.builder().email(user.getEmail()).displayName(user.getDisplayName()).build();
    }

}
