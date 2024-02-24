package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.name like '%' + :query + '%'")
    List<Product> findAllByName(String query);
    @Query("select p from Product p where p.id =: id")
    Optional<Product> findById(Long id);

    @Query("select p from Product p where  p.category.id =: categoryId")
    List<Product> findByCateAndCategoryId(Long categoryId);
}
