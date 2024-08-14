package com.example.ecommerce.domain.entities.file;

import com.example.ecommerce.domain.entities.product.ProductInventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_inventories_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductInventoryImage extends FileEntity{
    @OneToOne
    @JoinColumn(name = "product_inventory_id")
    private ProductInventory productInventory;
}
