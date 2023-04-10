package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exceptions.BaseException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {
        return new UserDto(userEntity.getEmail());
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
            throw new BaseException("The email address is already being used.");
        }

        String encodedPassword = passwordEncoder.encode(registration.getPassword());

        UserEntity userEntity = new UserEntity(registration.getEmail(), encodedPassword, registration.getDisplayName());
        userRepository.save(userEntity);
        return convertEntityToDto(userEntity);
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

        UserEntity user = opt.get();

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new BaseException("Password incorrect.");
        }

        // TODO: Return JWT Token

        return convertEntityToDto(user);
    }

}
