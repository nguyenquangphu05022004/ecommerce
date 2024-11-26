package com.example.ecommerce.production.service.spu;

import com.example.ecommerce.production.controller.spu.vo.ProductSpuCreateBaseReqVO;
import com.example.ecommerce.production.controller.spu.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;

public interface ProductSpuService {
    ProductSpu createProductSpu(ProductSpuCreateBaseReqVO reqVO);
    ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO);
    void getListProductSpu();
    ProductSpu updateProductSpuDetail();
}
