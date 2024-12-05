package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.product.controller.spu.detail.vo.ProductSpuDetailReqVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuDetail;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSpuDetailServiceImpl implements ProductSpuDetailService{
    private final ProductSpuDetailRepository productSpuDetailRepository;
    @Override
    public ProductSpuDetail addProductDetail(ProductSpuDetailReqVO reqVO) {
        ProductSpuDetail productSpuDetail = ProductSpuDetail.builder()
                .productProperty(ProductProperty.builder().id(reqVO.getPropertyId()).build())
                .productSpu(ProductSpu.builder().id(reqVO.getProductSpuId()).build())
                .productPropertyValue(ProductPropertyValue.builder().id(reqVO.getPropertyValueId()).build())
                .build();
        this.productSpuDetailRepository.save(productSpuDetail);
        return productSpuDetail;
    }

    @Override
    public ProductSpuDetail updateProductDetail(ProductSpuDetailReqVO reqVO) {
        return null;
    }

    @Override
    public void delete(Long productSpuDetailId) {
        this.productSpuDetailRepository.deleteById(productSpuDetailId);
    }

    @Override
    public List<ProductSpuDetail> getListBySpuId(Long spuId) {
        return this.productSpuDetailRepository.findAllByProductSpuId(spuId);
    }
}
