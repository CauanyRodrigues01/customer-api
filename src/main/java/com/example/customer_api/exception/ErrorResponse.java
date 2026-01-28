package com.example.customer_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Map<String, String> fieldErrors;

    public ErrorResponse(
        HttpStatus status,
        String message,
        String path,
        Map<String, String> fieldErrors
    ) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    // Error without field errors
    public static ErrorResponse from(
        HttpStatus status,
        String message,
        String path
    ) {
        return new ErrorResponse(
            status,
            message,
            path,
            null
        );
    }

    // Error with field errors
    public static ErrorResponse from(
        HttpStatus status,
        String message,
        String path,
        Map<String, String> fieldErrors
    ) {
        return new ErrorResponse(
            status,
            message,
            path,
            fieldErrors
        );
    }

}

