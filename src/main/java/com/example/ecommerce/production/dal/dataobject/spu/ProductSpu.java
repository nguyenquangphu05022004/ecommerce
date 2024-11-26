package com.example.ecommerce.production.dal.dataobject.spu;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.production.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.production.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "production_product_spus")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class ProductSpu extends BaseEntity {
    private String name;
    private String description;
    private Integer maxPrice;
    private Integer minPrice;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

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

    /**
     * List of detail about product
     */
    @OneToMany(mappedBy = "productSpu")
    private List<ProductSpuDetail> productSpuDetails;
}
