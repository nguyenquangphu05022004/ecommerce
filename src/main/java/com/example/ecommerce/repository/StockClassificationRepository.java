package com.example.ecommerce.repository;

import com.example.ecommerce.domain.StockClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockClassificationRepository extends JpaRepository<StockClassification, Long> {
}
