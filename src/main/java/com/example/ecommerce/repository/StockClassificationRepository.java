package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockClassificationRepository extends JpaRepository<Stock.StockClassification, Long> {
    Optional<Stock.StockClassification> findByIdAndStockId(Long id, Long stockId);
}
