package com.example.customer_api.address.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressUpdateRequest(

    @NotBlank
    @Size(max = 150)
    String street,

    @NotBlank
    @Size(max = 50)
    String city,

    @NotBlank
    @Size(max = 50)
    String state,

    @NotBlank
    @Size(max = 20)
    String zipCode

) {}
