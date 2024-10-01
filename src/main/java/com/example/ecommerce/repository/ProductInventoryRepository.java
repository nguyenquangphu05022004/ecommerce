package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
    Optional<ProductInventory> findByProductIdAndAttributeCombinationKey(Long productId,
                                                                         String attributeCombinationKey);

    Optional<ProductInventory> findByProductIdAndAttribute(
            Long productId,
            Long totalAttrMapValueId
    );
}
