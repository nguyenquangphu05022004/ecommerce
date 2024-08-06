package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.recommendation.ProductSimilarity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSimilarityRepository extends JpaRepository<ProductSimilarity, Long> {
}
