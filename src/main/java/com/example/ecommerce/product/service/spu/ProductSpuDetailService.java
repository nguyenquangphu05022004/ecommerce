package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.product.controller.spu.detail.vo.ProductSpuDetailReqVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuDetail;

import java.util.Collection;
import java.util.List;

public interface ProductSpuDetailService {
    ProductSpuDetail addProductDetail(ProductSpuDetailReqVO reqVO);
    ProductSpuDetail updateProductDetail(ProductSpuDetailReqVO reqVO);
    void delete(Long productSpuDetailId);

    List<ProductSpuDetail> getListBySpuId(Long spuId);
}
