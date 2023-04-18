package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exceptions.BaseException;
import com.zkorra.todorestdemo.exceptions.DuplicateException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public UserDto register(UserDto.Registration registration) throws BaseException {

        if (registration.getEmail().isEmpty()) {
            throw new NotFoundException("Email is undefined.");
        }

        if (registration.getPassword().isEmpty()) {
            throw new NotFoundException("Password is undefined.");
        }

        Optional<UserEntity> opt = userRepository.findByEmail(registration.getEmail());

        if (opt.isPresent()) {
            throw new DuplicateException("The email address is already being used.");
        }

        String encodedPassword = passwordEncoder.encode(registration.getPassword());

        UserEntity user = new UserEntity(registration.getEmail(), encodedPassword, registration.getDisplayName());
        userRepository.save(user);

        return new UserDto(user.getEmail(), "");
    }

    @Transactional(readOnly = true)
    public UserDto login(UserDto.Login login) throws BaseException {
        if (login.getEmail().isEmpty()) {
            throw new NotFoundException("Email is undefined.");
        }

        if (login.getPassword().isEmpty()) {
            throw new NotFoundException("Password is undefined.");
        }

        Optional<UserEntity> opt = userRepository.findByEmail(login.getEmail());

        if (opt.isEmpty()) {
            throw new NotFoundException("The account doesn't exist.");
        }

        UserEntity user= opt.get();

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new BaseException("Password incorrect.");
        }

        String jwtToken = jwtUtils.generateToken(user);

        return new UserDto(user.getEmail(), jwtToken);
    }

}
