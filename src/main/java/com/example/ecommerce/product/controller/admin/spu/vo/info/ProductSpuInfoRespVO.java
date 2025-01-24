package com.example.ecommerce.product.controller.admin.spu.vo.info;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuInfo;
import lombok.Data;
import lombok.Getter;

@Data
public class ProductSpuInfoRespVO {
    private Long id;
    private String propertyName;
    private String value;

    public ProductSpuInfoRespVO(ProductSpuInfo info) {
        this.id = info.getId();
        this.propertyName = info.getProperty().getName();
        this.value = info.getValue();
    }
}
