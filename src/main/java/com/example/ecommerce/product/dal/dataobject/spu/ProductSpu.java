package com.example.ecommerce.product.dal.dataobject.spu;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "production_product_spus")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class ProductSpu extends BaseEntity {
    private String name;
    private String description;
    private Integer maxPrice;
    private Integer minPrice;

    private String sendFrom;


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;


    private Boolean enable;

    /**
     * Category
     */
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    /**
     * brand
     */
    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrand productBrand;

    /**
     * Set of sku
     */
    @OneToMany(mappedBy = "productSpu")
    private Set<ProductSku> productSkus;

}
