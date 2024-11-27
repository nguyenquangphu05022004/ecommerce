package com.example.ecommerce.production.service.property;

import com.example.ecommerce.production.controller.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.production.dal.dataobject.properties.ProductPropertyValue;

import java.util.List;

public interface ProductPropertyValueService {

    ProductPropertyValue updateProductPropertyValue(ProductPropertyValueReqVO reqVO);
    ProductPropertyValue createProductPropertyValue(ProductPropertyValueReqVO reqVO);
    List<ProductPropertyValue> getListProductPropertyValueByPropertyId(Long propertyId);
}
