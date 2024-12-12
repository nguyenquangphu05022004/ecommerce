package com.example.ecommerce.realtime.dal.dataobject.live;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;

public class LiveProduct extends BaseEntity {
    private ProductSpu productSpu;
    private Boolean display;
}
