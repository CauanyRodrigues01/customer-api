package com.example.customer_api.customer.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.customer_api.customer.dto.request.CustomerCreateRequest;
import com.example.customer_api.customer.dto.request.CustomerPatchRequest;
import com.example.customer_api.customer.dto.request.CustomerUpdateRequest;
import com.example.customer_api.customer.dto.response.CustomerDetailsResponse;
import com.example.customer_api.customer.dto.response.CustomerResponse;
import com.example.customer_api.customer.dto.response.CustomerSummary;
import com.example.customer_api.customer.entity.Customer;
import com.example.customer_api.customer.mapper.CustomerMapper;
import com.example.customer_api.customer.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final PasswordEncoder passwordEncoder;

    private static final String CUSTOMER_NOT_FOUND = "Customer not found with id: ";
    private static final String EMAIL_ALREADY_IN_USE = "Email already in use";
    private static final String INACTIVE_CUSTOMER_UPDATE = "Inactive customer cannot be updated";

    // CREATE
    public CustomerDetailsResponse create(CustomerCreateRequest dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new IllegalStateException(EMAIL_ALREADY_IN_USE);
        }
// 2. Cria manualmente a entidade
    Customer customer = new Customer();
    customer.updateName(dto.name());
    customer.updateEmail(dto.email());
    customer.changePassword(passwordEncoder.encode(dto.password()));
    customer.changeRole(dto.role());
    customer.activate(); // define status como ACTIVE

    // 3. Salva no banco
    repository.save(customer);

    // 4. Retorna resposta detalhada
    return mapper.toDetailsResponse(customer); 
    }

    // READ BY ID SIMPLIFIED
    public CustomerResponse findById(Long id) {
        Customer customer = findByIdOrThrow(id);
        return mapper.toResponse(customer);
    }

    // READ BY ID DETAILED
    public CustomerDetailsResponse findWithAddressesById(Long id) {
        Customer customer = findByIdOrThrow(id);
        return mapper.toDetailsResponse(customer);
    }

    // LIST ALL ACTIVE
    public List<CustomerSummary> findAllActive() {
        return mapper.toSummaryList(repository.findAllByActiveTrue());
    }

    // UPDATE (PUT)
    public CustomerDetailsResponse update(Long id, CustomerUpdateRequest dto) {
        Customer customer = findByIdOrThrow(id);
        
        if (!customer.isActive()) {
            throw new IllegalStateException(INACTIVE_CUSTOMER_UPDATE);
        }

        mapper.updateEntityFromUpdateRequest(dto, customer);

        repository.save(customer);

        return mapper.toDetailsResponse(customer);
    }

    // UPDATE (PATCH)
    public CustomerDetailsResponse patch(Long id, CustomerPatchRequest dto) {
        Customer customer = findByIdOrThrow(id);
        
        if(!customer.isActive()) {
            throw new IllegalStateException(INACTIVE_CUSTOMER_UPDATE);
        }

        mapper.updateEntityFromPatchRequest(dto, customer);

        repository.save(customer);

        return mapper.toDetailsResponse(customer);
    }

    // SOFT DELETE
    public void deactivate(Long id) {
        Customer customer = findByIdOrThrow(id);
        
        if(customer.isActive()) {
            customer.deactivate();
        }
    }

    // ASSISTANTS

    public Customer findByIdOrThrow(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND + id));
    }

}
