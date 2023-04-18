package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exceptions.DuplicateException;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder, jwtUtils);
    }

    @Test
    void whenValidRegistration_thenSaveUserAndReturnUserDto() {
        UserDto.Registration registration = new UserDto.Registration("test@domain.com", "password", "display name");

        UserDto actual = userService.register(registration);

        verify(userRepository, times(1)).save(any(UserEntity.class));

        assertEquals(registration.getEmail(), actual.getEmail());
    }

    @Test
    void whenDuplicatedUserRegistration_thenThrowDuplicationException() {
        UserDto.Registration registration = new UserDto.Registration("test@domain.com", "password", "display name");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));

        assertThrows(DuplicateException.class, () -> userService.register(registration));

        verify(userRepository, times(1)).findByEmail(registration.getEmail());
    }
}
