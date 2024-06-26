package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByUserUsername(String username);
    Optional<Basket> findByUserIdAndStockIdAndStockClassificationId(Long userId,
                                                                    Long stockId,
                                                                    Long stockClassificationId);
    Long countAllByUserUsername(String username);

    @Modifying
    void deleteByStockIdAndUserId(Long id, Long id1);

}
