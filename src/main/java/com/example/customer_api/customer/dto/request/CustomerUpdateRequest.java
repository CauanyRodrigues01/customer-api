package com.example.customer_api.customer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerUpdateRequest(

    @NotBlank
    @Size(max = 50)
    String name,

    @NotBlank
    @Email
    String email

) {}
