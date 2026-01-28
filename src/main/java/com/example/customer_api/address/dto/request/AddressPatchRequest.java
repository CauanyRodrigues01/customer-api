package com.example.customer_api.address.dto.request;

import jakarta.validation.constraints.Size;

public record AddressPatchRequest(

    @Size(max = 150)
    String street,

    @Size(max = 50)
    String city,

    @Size(max = 50)
    String state,

    @Size(max = 20)
    String zipCode

) {}
