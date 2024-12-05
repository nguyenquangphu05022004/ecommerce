package com.example.ecommerce.product.controller.sku.vo.property;

import com.example.ecommerce.product.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.controller.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import lombok.Data;

@Data
public class ProductSkuPropertyResVO {
    private Long id;
    private ProductPropertyVO productProperty;
    private ProductPropertyValueResVO propertyValue;
    public ProductSkuPropertyResVO(ProductSkuProperty productSkuProperty) {
        this.id = productSkuProperty.getId();
        this.productProperty = new ProductPropertyVO(productSkuProperty.getProductProperty());
        this.propertyValue = new ProductPropertyValueResVO(productSkuProperty.getProductPropertyValue());
    }
}
