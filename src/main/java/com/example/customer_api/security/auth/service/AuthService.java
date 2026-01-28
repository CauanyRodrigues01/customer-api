package com.example.customer_api.security.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.customer_api.security.auth.dto.request.LoginRequest;
import com.example.customer_api.security.auth.dto.response.TokenResponse;
import com.example.customer_api.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse login(LoginRequest request) {

        Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
            );

        String token = jwtService.generateToken(authentication);

        return TokenResponse.bearer(token);
    }
}