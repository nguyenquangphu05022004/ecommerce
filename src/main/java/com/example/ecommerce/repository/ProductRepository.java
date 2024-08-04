package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Long id);

    @Query("select p from Product p where p.id != :id")
    List<Product> findAllDifferentId(Long id);
}
