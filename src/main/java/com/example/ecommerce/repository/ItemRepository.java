package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.order.Item;
import com.example.ecommerce.domain.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface   ItemRepository extends JpaRepository<Item, Long> {
    @Modifying
    void deleteByLineItem_Id(Long lineItemId);
}
