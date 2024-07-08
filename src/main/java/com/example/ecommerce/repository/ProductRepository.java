package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {



    @Query("select p from Product p inner join Category c on " +
            "p.category.id = c.id and c.id = :categoryId")
    Page<Product> findAllByCategoryId(@Param("categoryId") Long categoryId,
                                      Pageable pageable);
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    List<Product> findAllByVendorUserUsername(String username);
    List<Product> findAllByVendorId(Long id);


}
