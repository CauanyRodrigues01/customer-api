package com.example.customer_api.customer.dto.request;

import com.example.customer_api.customer.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerCreateRequest(

    @NotBlank
    @Size(min = 3, max = 50)
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 8)
    String password,

    @NotNull
    Role role

) {}
