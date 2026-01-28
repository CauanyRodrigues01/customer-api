package com.example.customer_api.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.example.customer_api.customer.enums.Role;

import java.io.IOException;

public class RoleDeserializer extends JsonDeserializer<Role> {
    
    @Override
    public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Role cannot be blank");
        }
        
        // Se o valor já tem ROLE_, usa direto
        if (value.startsWith("ROLE_")) {
            return Role.valueOf(value);
        }
        
        // Se não tem ROLE_, adiciona e converte
        String roleValue = "ROLE_" + value.toUpperCase();
        return Role.valueOf(roleValue);
    }
}
