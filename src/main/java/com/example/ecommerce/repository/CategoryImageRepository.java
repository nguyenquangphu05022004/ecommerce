package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryImageRepository extends JpaRepository<Category.CategoryImage, Long> {
    Optional<Category.CategoryImage> findByName(String name);
}
