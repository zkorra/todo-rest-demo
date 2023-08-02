package com.zkorra.todorestdemo.domain.user;

import com.zkorra.todorestdemo.domain.user.dto.UserDto;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import com.zkorra.todorestdemo.domain.user.service.UserService;
import com.zkorra.todorestdemo.exceptions.DuplicateException;
import com.zkorra.todorestdemo.exceptions.NotFoundException;
import com.zkorra.todorestdemo.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        UserDto.Registration registration = UserDto.Registration.builder()
                .email("test@test.com")
                .password("password123")
                .build();

        UserDto actual = userService.register(registration);

        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(passwordEncoder, times(1)).encode(registration.getPassword());

        assertEquals(registration.getEmail(), actual.getEmail());
        assertEquals(actual.getDisplayName(), "");
        assertNull(actual.getToken());
    }

    @Test
    void whenDuplicatedUserRegistration_thenThrowDuplicationException() {
        UserDto.Registration registration = UserDto.Registration.builder()
                .email("test@test.com")
                .password("password123")
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));

        assertThrows(DuplicateException.class, () -> userService.register(registration));

        verify(userRepository, times(1)).findByEmail(registration.getEmail());
    }

    @Test
    void whenValidLogin_thenReturnUserDto() {
        UserDto.Login login = UserDto.Login.builder().email("test@test.com").password("password123").build();

        UserEntity user = UserEntity.builder().email("test@test.com").password("encodedPassword").build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(login.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtils.generateToken(any(UserEntity.class))).thenReturn("JwtToken");

        UserDto actual = userService.login(login);

        assertEquals(login.getEmail(), actual.getEmail());
        assertNotNull(actual.getToken());
    }

    @Test
    void whenNotFoundUserLogin_thenThrow404() {
        UserDto.Login login = UserDto.Login.builder().email("test@test.com").password("password123").build();

        assertThrows(NotFoundException.class, () -> userService.login(login));
        verify(userRepository, times(1)).findByEmail(login.getEmail());
    }
}
