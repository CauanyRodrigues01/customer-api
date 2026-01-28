package com.example.customer_api.customer.controller;

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

import com.example.customer_api.customer.dto.request.CustomerCreateRequest;
import com.example.customer_api.customer.dto.request.CustomerPatchRequest;
import com.example.customer_api.customer.dto.request.CustomerUpdateRequest;
import com.example.customer_api.customer.dto.response.CustomerDetailsResponse;
import com.example.customer_api.customer.dto.response.CustomerResponse;
import com.example.customer_api.customer.dto.response.CustomerSummary;
import com.example.customer_api.customer.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDetailsResponse> create(
        @RequestBody @Valid CustomerCreateRequest dto
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<CustomerDetailsResponse> findWithAddress(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findWithAddressesById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerSummary>> findAllActive() {
        return ResponseEntity.ok(service.findAllActive());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDetailsResponse> update(
        @PathVariable Long id,
        @RequestBody @Valid CustomerUpdateRequest dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDetailsResponse> patch(
        @PathVariable Long id,
        @RequestBody @Valid CustomerPatchRequest dto
    ) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(
        @PathVariable Long id
    ) {
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }

}
