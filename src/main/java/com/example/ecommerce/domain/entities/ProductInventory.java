package com.example.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "product_inventories")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ProductInventory extends BaseEntity {
    @ManyToMany
    @JoinTable(name = "product_attribute_combination",
    joinColumns = @JoinColumn(name = "pro_inventory_id"),
    inverseJoinColumns = @JoinColumn(name = "pro_attr_map_value_id"))
    private List<ProductAttributeMappingValue> productAttributeMappingValues;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String skuCode;
    private Integer numberOfProductSold;
    private int price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> images;
}
