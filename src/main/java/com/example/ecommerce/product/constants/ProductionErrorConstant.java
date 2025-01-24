package com.example.ecommerce.product.constants;

import com.example.ecommerce.frame.common.exception.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    ErrorCode STOCK_NOT_ENOUGH = new ErrorCode("Stock not enough", BAD_REQUEST.value());
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
    ErrorCode PRODUCT_EVALUATION_NOT_FOUND = new ErrorCode("Product Evaluation not found", NOT_FOUND.value());
    ErrorCode PRODUCT_PROPERTY_NOT_FOUND = new ErrorCode("ProductProperty not found", NOT_FOUND.value());
    ErrorCode UPDATE_COMMENT_IS_DENIED = new ErrorCode("Create/Delete/Update comment is denied", BAD_REQUEST.value());
    ErrorCode YOUR_COMMENT_EXISTS = new ErrorCode("Your comment to product exists", BAD_REQUEST.value());
    /**
     * Property
     */
    ErrorCode PROPERTY_VALUE_NOT_FOUND = new ErrorCode("Property value not found", NOT_FOUND.value());

}
