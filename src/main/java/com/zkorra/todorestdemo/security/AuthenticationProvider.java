package com.zkorra.todorestdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider {
    private final AuthUserDetailsService authUserDetailsService;

    @Autowired
    public AuthenticationProvider(AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    public Authentication getAuthentication(String username) {
        AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(authUserDetails, authUserDetails.getPassword(), authUserDetails.getAuthorities());
    }

}
