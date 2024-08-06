package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Product.Language, Long> {}
