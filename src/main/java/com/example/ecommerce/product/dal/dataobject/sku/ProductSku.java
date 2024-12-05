package com.example.ecommerce.product.dal.dataobject.sku;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "production_product_skus")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class ProductSku extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;
    private Integer price;
    private String image;
    private Integer quantity;
    @OneToMany(mappedBy = "productSku")
    private List<ProductSkuProperty> productSkuProperties;
}
