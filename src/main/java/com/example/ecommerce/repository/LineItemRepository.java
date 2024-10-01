package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}
