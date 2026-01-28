package com.example.customer_api.address.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customer_api.address.dto.request.AddressCreateRequest;
import com.example.customer_api.address.dto.request.AddressPatchRequest;
import com.example.customer_api.address.dto.request.AddressUpdateRequest;
import com.example.customer_api.address.dto.response.AddressResponse;
import com.example.customer_api.address.entity.Address;
import com.example.customer_api.address.mapper.AddressMapper;
import com.example.customer_api.address.repository.AddressRepository;
import com.example.customer_api.customer.entity.Customer;
import com.example.customer_api.customer.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final AddressMapper mapper;

    private static final String ADDRESS_NOT_FOUND = "Address not found with id: ";
    private static final String FOR_CUSTOMER_ID = " for customer id: ";

    // CREATE
    public AddressResponse create(Long customerId, AddressCreateRequest dto) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        Address address = mapper.toEntity(dto);
        address.setCustomer(customer);

        addressRepository.save(address);

        return mapper.toResponse(address);
    }

    // READ BY ID
    public AddressResponse findById(Long customerId, Long addressId) {
        Address address = addressRepository
            .findByIdAndCustomerId(addressId, customerId)
            .orElseThrow(() -> new EntityNotFoundException(ADDRESS_NOT_FOUND + addressId + FOR_CUSTOMER_ID + customerId));

        return mapper.toResponse(address);
    }

    // LIST BY CUSTOMER
    public List<AddressResponse> findAllByCustomer(Long customerId) {
        return mapper.toResponseList(
            addressRepository.findAllByCustomerId(customerId)
        );
    }

    // UPDATE (PUT)
    public AddressResponse update(
        Long customerId,
        Long addressId,
        AddressUpdateRequest dto
    ) {
        Address address = addressRepository
            .findByIdAndCustomerId(addressId, customerId)
            .orElseThrow(() -> new EntityNotFoundException(ADDRESS_NOT_FOUND + addressId + FOR_CUSTOMER_ID + customerId));
        
        mapper.updateEntityFromUpdateRequest(dto, address);

        addressRepository.save(address);

        return mapper.toResponse(address);
    }


    // UPDATE (PATCH)
    public AddressResponse patch(
        Long customerId,
        Long addressId,
        AddressPatchRequest dto
    ) {
        Address address = addressRepository
            .findByIdAndCustomerId(addressId, customerId)
            .orElseThrow(() -> new EntityNotFoundException(ADDRESS_NOT_FOUND + addressId + FOR_CUSTOMER_ID + customerId));
        
        mapper.updateEntityFromPatchRequest(dto, address);

        addressRepository.save(address);

        return mapper.toResponse(address);
    }

    // DELETE
    public void delete(Long customerId, Long addressId) {
        Address address = addressRepository
            .findByIdAndCustomerId(addressId, customerId)
            .orElseThrow(() -> new EntityNotFoundException(ADDRESS_NOT_FOUND + addressId + FOR_CUSTOMER_ID + customerId));

        addressRepository.delete(address);
    }

}
