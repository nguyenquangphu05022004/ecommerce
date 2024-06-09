package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByUserUsername(String username);
    Optional<Basket> findByUserIdAndStockId(Long userId, Long stockId);
    Long countAllByUserUsername(String username);

    @Modifying
    void deleteByStockIdAndUserId(Long id, Long id1);
//    Basket updateBasketQuantityById(Long id, Integer quantity);

}
