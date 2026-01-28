package com.example.customer_api.customer.dto.response;

import com.example.customer_api.customer.enums.CustomerStatus;

public record CustomerResponse(

    Long id,
    String name,
    String email,
    CustomerStatus status

) {}
