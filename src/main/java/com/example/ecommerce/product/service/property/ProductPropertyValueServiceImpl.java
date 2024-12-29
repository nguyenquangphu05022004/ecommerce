package com.example.ecommerce.product.service.property;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PROPERTY_VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductPropertyValueServiceImpl implements ProductPropertyValueService{
    private final ProductPropertyValueRepository productPropertyValueRepository;
    @Override
    public ProductPropertyValue updateProductPropertyValue(ProductPropertyValueReqVO reqVO) {
        ProductPropertyValue value = getValueById(reqVO.getId()).toBuilder()
                .propertyValue(reqVO.getValue()).build();
        this.productPropertyValueRepository.save(value);
        return value;
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
    public PageResult<ProductPropertyValue> getPagePropertyValueByPropertyId(Long propertyId, PageParam pageParam) {
        return new PageResult<>(productPropertyValueRepository.findAllByProductPropertyId(propertyId, pageParam.buildPageRequest()));
    }

    @Override
    public ProductPropertyValue getValueById(Long id) {
        return this.productPropertyValueRepository.findById(id)
                .orElseThrow(() -> exception(PROPERTY_VALUE_NOT_FOUND));
    }
}
