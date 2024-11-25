package com.example.ecommerce.production.controller.category.vo;

import com.example.ecommerce.production.dal.dataobject.category.ProductCategory;
import lombok.Data;

@Data
public class ProductCategoryResVO {
    private Long id;
    private String name;
    private ProductCategoryResVO categoryParent;
    private String thumbnail;
    public ProductCategoryResVO(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.thumbnail = productCategory.getThumbnail();
        this.categoryParent = new ProductCategoryResVO(productCategory.getCategoryParent());
    }
}
