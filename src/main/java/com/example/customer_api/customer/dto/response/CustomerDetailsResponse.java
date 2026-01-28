package com.example.customer_api.customer.dto.response;

import java.util.List;

import com.example.customer_api.address.dto.response.AddressResponse;
import com.example.customer_api.customer.enums.CustomerStatus;
import com.example.customer_api.customer.enums.Role;

public record CustomerDetailsResponse(

    Long id,
    String name,
    String email,
    Role role,
    CustomerStatus status,
    List<AddressResponse> addresses

) {}
