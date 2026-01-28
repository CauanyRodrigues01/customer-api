package com.example.customer_api.address.dto.response;

import com.example.customer_api.address.entity.Address;

public record AddressResponse(
    String street,
    String city,
    String state,
    String zipCode
) {

    public static AddressResponse fromEntity(Address address) {
        return new AddressResponse(
            address.getStreet(),
            address.getCity(),
            address.getState(),
            address.getZipCode()
        );
    }

}
