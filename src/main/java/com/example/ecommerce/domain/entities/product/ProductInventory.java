package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_inventories")
@NoArgsConstructor
@Getter
@Setter
public class ProductInventory extends BaseEntity {
    private String attributeCombinationKey;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String skuCode;
}