package com.example.ecommerce.production.service.spu;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;

public interface ProductSpuService {
    ProductSpu createProductSpu(ProductSpuCreateReqVO reqVO);
    ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO);
    PageResult<ProductSpu> getListProductSpu();
}
