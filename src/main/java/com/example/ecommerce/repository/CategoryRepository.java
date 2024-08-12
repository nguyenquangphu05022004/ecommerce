package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByParentIsNull(Pageable pageable);

}
