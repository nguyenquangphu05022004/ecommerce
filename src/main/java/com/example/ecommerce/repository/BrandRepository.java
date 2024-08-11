package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<ProductBrand, Long> {
}
