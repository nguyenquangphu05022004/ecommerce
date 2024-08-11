package com.example.ecommerce.service.algorithm.search;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductFilterSimpleFactory {
    public static ProductFilterStrategy getInstance(
            ProductFilterType type
    ) {
        return switch (type){
            case CATEGORY_PARENT -> new ProductFilterCategoryParent();
            case CATEGORY_CHILDREN -> new ProductFilterCategoryChildren();
            case NAME -> new ProductFilterName();
            case BRAND -> new ProductFilterBrand();
            case PRICE -> new ProductFilterPrice();
            case VENDOR -> new ProductFilterVendor();
        };
    }
}
