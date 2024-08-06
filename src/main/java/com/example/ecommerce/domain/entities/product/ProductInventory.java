package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_inventories")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ProductInventory extends BaseEntity {
    private String attributeCombinationKey;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String skuCode;
    private Integer numberOfProductSold;

    public ProductInventory(Long id) {
        super(id);
    }
}
