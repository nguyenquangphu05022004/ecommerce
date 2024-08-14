package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.ProductInventoryImage;
import jakarta.persistence.*;
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
    @OneToOne(mappedBy = "productInventory")
    private ProductInventoryImage imageRepresent;
    public ProductInventory(Long id) {
        super(id);
    }
}
