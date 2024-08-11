package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order>  findAllByCreatedByAndOrderStatus(String createdBy, OrderStatus orderStatus, Pageable pageable);
    Page<Order> findAllByCreatedBy(String username, Pageable pageable);
}
