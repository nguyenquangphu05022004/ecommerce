package com.example.ecommerce.production.service.sku;

import com.example.ecommerce.production.controller.sku.vo.property.ProductSkuPropertyCreateReqVO;
import com.example.ecommerce.production.controller.sku.vo.property.ProductSkuPropertyUpdateReqVO;
import com.example.ecommerce.production.dal.dataobject.sku.ProductSkuProperty;

import java.util.List;

public interface ProductSkuPropertyService {
    ProductSkuProperty createProductSkuProperty(ProductSkuPropertyCreateReqVO reqVO);
    ProductSkuProperty updateProductSkuProperty(ProductSkuPropertyUpdateReqVO reqVO);
    List<ProductSkuProperty> getListPropertyByProductSkuId(Long productSkuId);
    void deleteById(Long id);

}
