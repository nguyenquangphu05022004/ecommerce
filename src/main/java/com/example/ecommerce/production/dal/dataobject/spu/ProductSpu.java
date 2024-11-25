package com.example.ecommerce.production.dal.dataobject.spu;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.production.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.production.dal.dataobject.category.ProductCategory;

public class ProductSpu extends BaseEntity {
    private String name;
    private String description;
    private Long maxPrice;
    private Long minPrice;

    private ProductCategory category;
    private ProductBrand brand;
}
