package com.example.ecommerce.product.service.property;

import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;

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
    List<ProductProperty> getListProductProperty();
    ProductProperty getById(Long id);
}
