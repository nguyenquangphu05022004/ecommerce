package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.file.CategoryImage;
import com.example.ecommerce.domain.entities.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long> {
    Optional<CategoryImage> findByName(String name);
}
