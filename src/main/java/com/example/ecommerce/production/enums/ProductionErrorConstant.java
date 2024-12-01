package com.example.ecommerce.production.enums;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public interface ProductionErrorConstant {
    /**
     * ------------------Product Brand-----------------
     */
    ErrorCode BRAND_NOT_FOUND = new ErrorCode("ProductBrand not found", NOT_FOUND.value());

    /**
     *------------------Product Category----------------
     */
    ErrorCode CATEGORY_NOT_FOUND = new ErrorCode("ProductCategory not found", NOT_FOUND.value());
    /**
     * ------Product sku
     */
    ErrorCode PRODUCT_SKU_NOT_FOUND  = new ErrorCode("ProductSku not found", NOT_FOUND.value());
    ErrorCode PRODUCT_SKU_PROPERTY_NOT_FOUND = new ErrorCode("ProductSkuProperty not found", NOT_FOUND.value());
    /**
     * ------Product spu
     */
    ErrorCode PRODUCT_SPU_NOT_FOUND =new ErrorCode("ProductSpu not found", NOT_FOUND.value());
    /**
     * ------Product comment
     */
    ErrorCode SELLER_NOT_FOUND = new ErrorCode("Seller not found", NOT_FOUND.value());
    ErrorCode PRODUCT_COMMENT_NOT_FOUND = new ErrorCode("ProductComment not found", NOT_FOUND.value());
}
