package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
}
