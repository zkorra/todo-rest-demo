package com.zkorra.todorestdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;


    @Autowired
    public JwtAuthFilter(JwtUtils jwtUtils, AuthenticationProvider authenticationProvider) {
        this.jwtUtils = jwtUtils;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = header.replace(TOKEN_PREFIX, "");

        if (!jwtUtils.validateToken(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String username = jwtUtils.getUsernameFromToken(token);

        Authentication auth = authenticationProvider.getAuthentication(username);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
