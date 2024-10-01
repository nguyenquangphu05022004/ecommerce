package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.ProductAttributeMappingValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeMappingValueRepository extends JpaRepository<ProductAttributeMappingValue, Long> {
}
