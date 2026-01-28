package com.example.customer_api.address.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.customer_api.address.dto.request.AddressCreateRequest;
import com.example.customer_api.address.dto.request.AddressPatchRequest;
import com.example.customer_api.address.dto.request.AddressUpdateRequest;
import com.example.customer_api.address.dto.response.AddressResponse;
import com.example.customer_api.address.entity.Address;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AddressMapper {

    // create
    Address toEntity(AddressCreateRequest request);

    // update (put)
    void updateEntityFromUpdateRequest(AddressUpdateRequest request, @MappingTarget Address address);

    // update (patch)
    void updateEntityFromPatchRequest(AddressPatchRequest  request, @MappingTarget Address address);

    // responses
    AddressResponse toResponse(Address address);
    List<AddressResponse> toResponseList(List<Address> addresses);

}
