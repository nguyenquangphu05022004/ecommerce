package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.recommendation.ProductActionCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCacheRepository extends JpaRepository<ProductActionCache, Long> {
    ProductActionCache findByUserUsernameAndProductId(
            String username,
            Long id
    );
}
