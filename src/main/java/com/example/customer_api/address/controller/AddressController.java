package com.example.customer_api.address.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer_api.address.dto.request.AddressCreateRequest;
import com.example.customer_api.address.dto.request.AddressPatchRequest;
import com.example.customer_api.address.dto.request.AddressUpdateRequest;
import com.example.customer_api.address.dto.response.AddressResponse;
import com.example.customer_api.address.service.AddressService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers/{customerId}/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<AddressResponse> create(
        @PathVariable Long customerId,
        @RequestBody @Valid AddressCreateRequest dto
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(customerId, dto));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> findAll(
        @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(service.findAllByCustomer(customerId));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> update(
        @PathVariable Long customerId,
        @PathVariable Long addressId,
        @RequestBody @Valid AddressUpdateRequest dto
    ) {
        return ResponseEntity.ok(service.update(customerId, addressId, dto));
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponse> patch(
        @PathVariable Long customerId,
        @PathVariable Long addressId,
        @RequestBody @Valid AddressPatchRequest dto
    ) {
        return ResponseEntity.ok(service.patch(customerId, addressId, dto));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(
        @PathVariable Long customerId,
        @PathVariable Long addressId
    ) {
        service.delete(customerId, addressId);
        return ResponseEntity
            .noContent()
            .build();
    }

}
