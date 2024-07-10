package com.example.ecommerce.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductType {
    PRODUCT_HAVE_COLOR_SIZE("Có cả màu và size"),
    PRODUCT_HAS_COLOR("Chỉ có màu"),
    PRODUCT_HAS_SIZE("Chỉ có size"),
    PRODUCT_NOT_COLOR_SIZE("Không có màu và size");
    @Getter
    private final String type;
}
