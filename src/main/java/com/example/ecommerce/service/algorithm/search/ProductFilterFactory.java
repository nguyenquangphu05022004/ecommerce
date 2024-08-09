package com.example.ecommerce.service.algorithm.search;

public class ProductFilterFactory {
    public static ProductFilterStrategy getInstance(
            ProductFilterType type,
            FilterData filterData
    ) {
        return switch (type){
            case CATEGORY_PARENT -> new ProductFilterCategoryParent(filterData);
            case CATEGORY_CHILDREN -> new ProductFilterCategoryChildren(filterData);
            case NAME -> new ProductFilterName(filterData);
            case BRAND -> new ProductFilterBrand(filterData);
            case PRICE -> new ProductFilterPrice(filterData);
            case VENDOR -> new ProductFilterVendor(filterData);
        };
    }
}
