package com.example.ecommerce.production.service.property;

import com.example.ecommerce.production.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.production.controller.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.production.dal.dataobject.properties.ProductPropertyValue;

import java.util.List;

public interface ProductPropertyService {
    ProductProperty createProductProperty(ProductPropertyVO reqVO);
    ProductProperty updateProductProperty(ProductPropertyVO reqVO);

    /**
     * who did create property?
     * @param userOwnerId: user Id
     * @return: List properties those were created
     */
    List<ProductProperty> getListProductPropertyByOwner(Long userOwnerId);

}
