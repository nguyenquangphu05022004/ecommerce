package com.example.ecommerce.product.controller.app.spu.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.controller.admin.sku.vo.property.ProductSkuPropertyResVO;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import lombok.Getter;

import java.util.List;

@Getter
public class AppProductSkuRespVO {
    private Integer price;
    private String imageUrl;
    private Integer inStock;

    private List<ProductSkuPropertyResVO> properties;

    public AppProductSkuRespVO(ProductSku sku) {
        this.price = sku.getPrice();
        this.imageUrl = sku.getImage();
        this.inStock = sku.getQuantity();
        this.properties = CollUtils.convertList(sku.getProductSkuProperties(), ProductSkuPropertyResVO::new);
    }
}
