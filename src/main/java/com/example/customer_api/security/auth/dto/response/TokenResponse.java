package com.example.customer_api.security.auth.dto.response;

public record TokenResponse(
    String token,
    String type
) {
    public static TokenResponse bearer(String token) {
        return new TokenResponse(token, "Bearer");
    }
}
