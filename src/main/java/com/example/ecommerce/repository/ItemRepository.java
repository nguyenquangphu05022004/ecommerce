package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface   ItemRepository extends JpaRepository<Order.Item, Long> {
}
