package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockImageRepository extends JpaRepository<Stock.StockImage, Long> {
    Optional<Stock.StockImage> findByName(String name);

}