package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<ProductInventory, Long> {
    Optional<ProductInventory> findByProductIdAndAttributeCombinationKey(Long productId,
                                                                         String attributeCombinationKey);
}
