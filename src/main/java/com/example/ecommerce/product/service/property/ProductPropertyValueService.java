package com.example.ecommerce.product.service.property;

import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;

import java.util.List;

public interface ProductPropertyValueService {

    ProductPropertyValue updateProductPropertyValue(ProductPropertyValueReqVO reqVO);
    ProductPropertyValue createProductPropertyValue(ProductPropertyValueReqVO reqVO);
    List<ProductPropertyValue> getListProductPropertyValueByPropertyId(Long propertyId);
}
