package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%")
    List<Product> findAllByLanguageNameEnOrNameVn(@Param("query") String query);
    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
            "where lower(p.language.nameEn) " +
            "like  %:query% or lower(p.language.nameVn) " +
            "like %:query%")
    Page<Product> findAllByLanguageNameEnOrNameVnAAndCategory(@Param("query") String query,
                                                              @Param("categoryId") Long categoryId,
                                                              Pageable pageable);
    Optional<Product> findById(Long id);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findAllByVendorUserUsername(String username);
    Page<Product> findAllByVendorId(Long vendorId, Pageable pageable);

}
