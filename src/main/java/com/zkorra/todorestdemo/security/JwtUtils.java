package com.zkorra.todorestdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zkorra.todorestdemo.domain.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.expireTime}")
    private long expireTimeMs;

    public String generateToken(UserEntity user) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireTimeMs * 1000);

        return JWT.create().withSubject(user.getId()).withExpiresAt(expiryDate).sign(getAlgorithm());
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(getAlgorithm()).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(getAlgorithm()).build().verify(token);
        return decodedJWT.getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

}
