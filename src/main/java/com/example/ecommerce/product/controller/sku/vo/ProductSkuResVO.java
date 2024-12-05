package com.example.ecommerce.product.controller.sku.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import lombok.Data;

import static com.example.ecommerce.frame.common.string.StringUtils.convertToString;

@Data
public class ProductSkuResVO extends ProductSkuBaseVO{
    private Long id;
    private String propertyValues;
    public ProductSkuResVO() {
    }
    public ProductSkuResVO(ProductSku productSku) {
        super(productSku);
        this.id = productSku.getId();
        this.propertyValues = convertToString(productSku.getProductSkuProperties(), s -> s.getProductPropertyValue().getValue(), "_");
    }
}
