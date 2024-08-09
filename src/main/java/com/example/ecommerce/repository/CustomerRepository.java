package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.auth.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
