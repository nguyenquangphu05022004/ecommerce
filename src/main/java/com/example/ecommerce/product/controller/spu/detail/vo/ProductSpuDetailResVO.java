package com.example.ecommerce.product.controller.spu.detail.vo;

import com.example.ecommerce.product.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.controller.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuDetail;
import lombok.Getter;

@Getter
public class ProductSpuDetailResVO {
    private Long id;
    private ProductPropertyVO productProperty;
    private ProductPropertyValueResVO productPropertyValue;
    public ProductSpuDetailResVO(ProductSpuDetail req) {
        this.id = req.getId();
        this.productProperty = new ProductPropertyVO(req.getProductProperty());
        this.productPropertyValue = new ProductPropertyValueResVO(req.getProductPropertyValue());
    }
}
