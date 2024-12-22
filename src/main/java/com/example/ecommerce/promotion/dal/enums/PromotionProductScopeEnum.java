package com.example.ecommerce.promotion.dal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromotionProductScopeEnum {
    ALL("Tat ca san pham"),
    SPU("Spu duoc chi dinh"),
    SKU("Sku duoc chi dinh"),
    CATEGORY("The loai duoc chi dinh");
    @Getter
    private final String name;
}
