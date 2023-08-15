package com.zkorra.todorestdemo.domain.user.service;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.exception.ResourceConflictException;
import com.zkorra.todorestdemo.exception.ResourceNotFoundException;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    void whenValidRegistration_thenSaveUserAndReturnUserDto() {
        UserDto.Registration registration = UserDto.Registration.builder()
                .email("test@test.com")
                .password("password123")
                .build();

        UserDto actual = authService.register(registration);

        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(passwordEncoder, times(1)).encode(registration.getPassword());

        assertEquals(registration.getEmail(), actual.getEmail());
        assertEquals("", actual.getDisplayName());
        assertNull(actual.getToken());
    }

    @Test
    void whenDuplicatedUserRegistration_thenThrowResourceConflictException() {
        UserDto.Registration registration = UserDto.Registration.builder()
                .email("test@test.com")
                .password("password123")
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));

        assertThrows(ResourceConflictException.class, () -> authService.register(registration));

        verify(userRepository, times(1)).findByEmail(registration.getEmail());
    }

    @Test
    void whenValidLogin_thenReturnUserDto() {
        UserDto.Login login = UserDto.Login.builder().email("test@test.com").password("password123").build();

        UserEntity user = UserEntity.builder().email("test@test.com").password("encodedPassword").build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(login.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtils.generateToken(any(UserEntity.class))).thenReturn("JwtToken");

        UserDto actual = authService.login(login);

        assertEquals(login.getEmail(), actual.getEmail());
        assertNotNull(actual.getToken());
    }

    @Test
    void whenNotFoundUserLogin_thenThrow404() {
        UserDto.Login login = UserDto.Login.builder().email("test@test.com").password("password123").build();

        assertThrows(ResourceNotFoundException.class, () -> authService.login(login));
        verify(userRepository, times(1)).findByEmail(login.getEmail());
    }
}
