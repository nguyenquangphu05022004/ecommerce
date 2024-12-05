package com.example.ecommerce.product.service.sku;

import com.example.ecommerce.product.controller.sku.vo.property.ProductSkuPropertyCreateReqVO;
import com.example.ecommerce.product.controller.sku.vo.property.ProductSkuPropertyUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PRODUCT_SKU_PROPERTY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductSkuPropertyServiceImpl implements ProductSkuPropertyService{
    private final ProductSkuPropertyRepository productSkuPropertyRepository;

    @Override
    public ProductSkuProperty createProductSkuProperty(ProductSkuPropertyCreateReqVO reqVO) {
        ProductSkuProperty productSkuProperty = ProductSkuProperty.builder()
                .productSku(ProductSku.builder().id(reqVO.getProductSkuId()).build())
                .productPropertyValue(ProductPropertyValue.builder().id(reqVO.getPropertyValueId()).build())
                .productProperty(ProductProperty.builder().id(reqVO.getPropertyId()).build())
                .build();
        return this.productSkuPropertyRepository.save(productSkuProperty);
    }

    @Override
    public ProductSkuProperty updateProductSkuProperty(ProductSkuPropertyUpdateReqVO reqVO) {
        ProductSkuProperty productSkuProperty = this.productSkuPropertyRepository.findById(reqVO.getId())
                .orElseThrow(() -> exception(PRODUCT_SKU_PROPERTY_NOT_FOUND)).toBuilder()
                .productSku(ProductSku.builder().id(reqVO.getProductSkuId()).build())
                .productPropertyValue(ProductPropertyValue.builder().id(reqVO.getPropertyValueId()).build())
                .productProperty(ProductProperty.builder().id(reqVO.getPropertyId()).build())
                .build();
        return this.productSkuPropertyRepository.save(productSkuProperty);
    }

    @Override
    public List<ProductSkuProperty> getListProductSkuPropertyByProductSkuId(Long productSkuId) {
        return this.productSkuPropertyRepository.findAllByProductSkuId(productSkuId);
    }

    @Override
    public void deleteById(Long id) {
        this.productSkuPropertyRepository.deleteById(id);
    }

    @Override
    public List<ProductSkuProperty> getListProductSkuPropertyByProductSpuId(Long productSpuId) {
        return null;
    }
}
