package com.example.customer_api.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.customer_api.customer.entity.Customer;
import com.example.customer_api.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Customer customer = repository.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException("Customer not found with email: " + email
            )
        );

        if (!customer.isActive()) {
            throw new UsernameNotFoundException("Customer is inactive");
        }

        return User.builder()
            .username(customer.getEmail())
            .password(customer.getPassword())
            .roles(customer.getRole().name())
            .build();
    }
}
