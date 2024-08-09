package com.example.ecommerce.service.algorithm.sort;

public class ProductSortFactory {
    public static ProductSortStrategy getInstance(ProductSortType type) {
        return switch (type) {
            case PRICE -> new ProductSortPrice();
            case RATE_AVERAGE -> new ProductSortSold();
            case PRODUCT_SOLD -> new ProductSortRateAverage();
        };
    }
}
