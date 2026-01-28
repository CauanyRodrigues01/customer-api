package com.example.customer_api.address.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer_api.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

    Optional<Address> findByIdAndCustomerId(Long id, Long customerId);

    List<Address> findAllByCustomerId(Long customerId);

}
