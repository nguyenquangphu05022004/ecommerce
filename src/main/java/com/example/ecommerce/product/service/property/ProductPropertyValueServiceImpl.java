package com.example.ecommerce.product.service.property;

import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPropertyValueServiceImpl implements ProductPropertyValueService{
    private final ProductPropertyValueRepository productPropertyValueRepository;
    @Override
    public ProductPropertyValue updateProductPropertyValue(ProductPropertyValueReqVO reqVO) {
        return null;
    }

    @Override
    public ProductPropertyValue createProductPropertyValue(ProductPropertyValueReqVO reqVO) {
        ProductPropertyValue productPropertyValue = ProductPropertyValue.builder().propertyValue(reqVO.getValue())
                .productProperty(ProductProperty.builder().id(reqVO.getPropertyId()).build())
                .build();
        this.productPropertyValueRepository.save(productPropertyValue);
        return productPropertyValue;
    }

    @Override
    public List<ProductPropertyValue> getListProductPropertyValueByPropertyId(Long propertyId) {
        return this.productPropertyValueRepository.findAllByProductPropertyId(propertyId);
    }
}
