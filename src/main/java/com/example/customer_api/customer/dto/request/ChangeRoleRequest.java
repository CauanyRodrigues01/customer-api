package com.example.customer_api.customer.dto.request;

import com.example.customer_api.customer.enums.Role;

import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest(
    @NotNull Role role
) {}
