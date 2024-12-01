package com.example.ecommerce.production.controller.sku.vo;

import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import lombok.Data;

@Data
public class ProductSkuResVO extends ProductSkuBaseVO{
    private Long id;
    public ProductSkuResVO() {
    }
    public ProductSkuResVO(ProductSku productSku) {
        super(productSku);
        this.id = productSku.getId();
    }
}
