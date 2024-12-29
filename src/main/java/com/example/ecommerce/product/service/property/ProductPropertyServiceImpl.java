package com.example.ecommerce.product.service.property;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PRODUCT_PROPERTY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductPropertyServiceImpl implements ProductPropertyService{
    private final ProductPropertyRepository productPropertyRepository;
    @Override
    public ProductProperty createProductProperty(ProductPropertyVO reqVO) {
        ProductProperty productProperty = ProductProperty.builder().name(reqVO.getName()).build();
        this.productPropertyRepository.save(productProperty);
        return productProperty;
    }

    @Override
    public ProductProperty updateProductProperty(ProductPropertyVO reqVO) {
        ProductProperty property = getById(reqVO.getId()).toBuilder().name(reqVO.getName()).build();
        this.productPropertyRepository.save(property);
        return property;
    }

    @Override
    public ProductProperty getById(Long id) {
        return this.productPropertyRepository.findById(id).orElseThrow(() -> exception(PRODUCT_PROPERTY_NOT_FOUND));
    }

    @Override
    public PageResult<ProductProperty> getPageProperty(PageParam req) {
        return new PageResult<>(productPropertyRepository.findAll(req.buildPageRequest()));
    }

    @Override
    public PageResult<ProductProperty> getPagePropertyByOwnerId(Long userId, PageParam req) {
        return new PageResult<>(productPropertyRepository.findAllByCreatedBy(userId, req.buildPageRequest()));
    }

}
