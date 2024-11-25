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
}
