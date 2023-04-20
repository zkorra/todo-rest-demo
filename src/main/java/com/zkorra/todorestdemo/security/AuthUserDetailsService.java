package com.zkorra.todorestdemo.security;

import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import com.zkorra.todorestdemo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> opt = userRepository.findByEmail(username);

        if (opt.isEmpty()) {
            return null;
        }

        UserEntity user = opt.get();

        return new AuthUserDetails(user.getId(), user.getEmail());
    }

    public AuthUserDetails loadUserByEmail(String email) {
        return this.loadUserByUsername(email);
    }
}
