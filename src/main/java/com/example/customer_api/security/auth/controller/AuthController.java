package com.example.customer_api.security.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer_api.security.auth.dto.request.LoginRequest;
import com.example.customer_api.security.auth.dto.response.TokenResponse;
import com.example.customer_api.security.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(
        @RequestBody @Valid LoginRequest request
    ) {
        return authService.login(request);
    }
    
}
