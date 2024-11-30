package com.example.ecommerce.production.service.property;

import com.example.ecommerce.production.controller.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.production.dal.dataobject.properties.ProductPropertyValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyValueServiceImpl implements ProductPropertyValueService{
    @Override
    public ProductPropertyValue updateProductPropertyValue(ProductPropertyValueReqVO reqVO) {
        return null;
    }

    @Override
    public ProductPropertyValue createProductPropertyValue(ProductPropertyValueReqVO reqVO) {
        return null;
    }

    @Override
    public List<ProductPropertyValue> getListProductPropertyValueByPropertyId(Long propertyId) {
        return null;
    }
}
