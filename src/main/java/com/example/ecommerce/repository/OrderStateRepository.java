package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderStateRepository extends JpaRepository<OrderStateMessage, Long> {
    List<OrderStateMessage> findAllByOrderId(Long orderId);
}
