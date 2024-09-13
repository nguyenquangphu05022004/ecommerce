package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.auth.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    void deleteByCreatedBy(String createdBy);
}
