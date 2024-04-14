package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByUserUsername(String username);
    Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);
    Long countAllByUserUsername(String username);
//    Basket updateBasketQuantityById(Long id, Integer quantity);
    void deleteByProductIdAndUserId(Long productId, Long userId);
}
