package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("select p from Product p where p.name like '%' + :query + '%'")
    List<Product> findAllByName(String query);
    Optional<Product> findById(Long id);
    List<Product> findByCategoryId(Long categoryId);

}
