package com.example.ecommerce.domain.entities.file;

import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "stock_images")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public  class ProductImage extends FileEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
