package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.recommendation.ProductActionCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductCacheRepository extends JpaRepository<ProductActionCache, Long> {
    @Query("select pc from User u inner join ProductActionCache pc on u.id = pc.user.id" +
            " inner join Product p on pc.product.id = p.id " +
            "where p.id = :productId and u.username = :username")
    Optional<ProductActionCache> findByProductIdAndUserUsername(
            @Param("productId") Long productId,
            @Param("username") String username
    );
}
