package com.example.ecommerce.product.service.property;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;

import java.util.List;

public interface ProductPropertyValueService {

    ProductPropertyValue updateProductPropertyValue(ProductPropertyValueReqVO reqVO);
    ProductPropertyValue createProductPropertyValue(ProductPropertyValueReqVO reqVO);
    PageResult<ProductPropertyValue> getPagePropertyValueByPropertyId(Long propertyId, PageParam pageParam);

    ProductPropertyValue getValueById(Long id);
}
