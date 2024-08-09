package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    List<LineItem> findAllByOrderId(Long orderId);
}
