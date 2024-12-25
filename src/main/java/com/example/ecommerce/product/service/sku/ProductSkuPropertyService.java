package com.example.ecommerce.product.service.sku;

import com.example.ecommerce.product.controller.admin.sku.vo.property.ProductSkuPropertyCreateReqVO;
import com.example.ecommerce.product.controller.admin.sku.vo.property.ProductSkuPropertyUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;

import java.util.List;

public interface ProductSkuPropertyService {
    ProductSkuProperty createProductSkuProperty(ProductSkuPropertyCreateReqVO reqVO);
    ProductSkuProperty updateProductSkuProperty(ProductSkuPropertyUpdateReqVO reqVO);
    List<ProductSkuProperty> getListProductSkuPropertyByProductSkuId(Long productSkuId);
    void deleteById(Long id);

     List<ProductSkuProperty> getListProductSkuPropertyByProductSpuId(Long productSpuId);
}
