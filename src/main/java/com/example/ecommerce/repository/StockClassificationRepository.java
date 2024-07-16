package com.example.ecommerce.repository;

import com.example.ecommerce.domain.StockClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockClassificationRepository extends JpaRepository<StockClassification, Long> {
    Optional<StockClassification> findByIdAndStockId(Long id, Long stockId);
}
