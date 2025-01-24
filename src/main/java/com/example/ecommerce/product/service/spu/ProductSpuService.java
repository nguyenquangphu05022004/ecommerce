package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductDetailsRespVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.product.controller.admin.spu.vo.PageProductSpuReqVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.product.controller.admin.spu.vo.info.ProductSpuInfoCreateReqVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuInfo;

public interface ProductSpuService {
    ProductSpu createProductSpu(ProductSpuCreateReqVO reqVO);
    ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO);

    PageResult<ProductSpu> getPageProductSpu(PageProductSpuReqVO reqVO);
    PageResult<ProductSpu> getPageProductSpuByUserId(Long userMemberId, int page);

    ProductSpu getProductSpuById(Long productSpuId);


    /**
     * Details about product when customer click
     * @param productSpuId
     * @return
     */
    ProductDetailsRespVO getDetailsProduct(Long productSpuId);


    ProductSpuInfo addProductInfo(ProductSpuInfoCreateReqVO req);
    void removeProductInfo(Long spuId, Long productInfoId);


}
