package com.example.ecommerce.repository;

import com.example.ecommerce.domain.StockImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockImageRepository extends JpaRepository<StockImage, Long> {
    Optional<StockImage> findByName(String name);

}
