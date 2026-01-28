package com.example.customer_api.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer_api.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    Optional<Customer> findWithAddressesById(Long id);

    Optional<Customer> findByIdAndActiveTrue(Long id);

    List<Customer> findAllByActiveTrue();

}
