package com.example.ecommerce.production.service.spu;

import com.example.ecommerce.production.controller.spu.detail.vo.ProductSpuDetailReqVO;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpuDetail;

public interface ProductSpuDetailService {
    ProductSpuDetail addProductDetail(ProductSpuDetailReqVO reqVO);
    ProductSpuDetail updateProductDetail(ProductSpuDetailReqVO reqVO);
    void delete(Long productSpuDetailId);
}
