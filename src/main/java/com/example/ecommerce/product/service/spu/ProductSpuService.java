package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuSearchReqVO;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;

public interface ProductSpuService {
    ProductSpu createProductSpu(ProductSpuCreateReqVO reqVO);
    ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO);
    PageResult<ProductSpu> searchProduct(ProductSpuSearchReqVO reqVO);
    PageResult<ProductSpu> getListProductSpuBySeller(Long userMemberId, int page);
    ProductSpu getProductSpuById(Long productSpuId);
}
