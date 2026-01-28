package com.example.customer_api.customer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CustomerPatchRequest(

    @Size(max = 50)
    String name,

    @Email
    String email

) {}
