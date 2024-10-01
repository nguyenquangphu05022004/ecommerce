package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order>  findAllByCreatedByAndStateName(String createdBy, String stateName, Pageable pageable);
    Page<Order> findAllByCreatedBy(String username, Pageable pageable);
}
