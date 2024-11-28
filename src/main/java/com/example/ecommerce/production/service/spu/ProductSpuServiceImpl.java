package com.example.ecommerce.production.service.spu;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.dal.repository.spu.ProductSpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.production.enums.ProductionErrorConstant.PRODUCT_SPU_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductSpuServiceImpl implements ProductSpuService{
    private final ProductSpuRepository productSpuRepository;
    @Override
    public ProductSpu createProductSpu(ProductSpuCreateReqVO reqVO) {
        return null;
    }

    @Override
    public ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO) {
        return null;
    }

    @Override
    public PageResult<ProductSpu> getListProductSpu() {
        return null;
    }

    @Override
    public ProductSpu getProductSpuById(Long productSpuId) {
        return this.productSpuRepository.findById(productSpuId)
                .orElseThrow(() -> exception(PRODUCT_SPU_NOT_FOUND));
    }
}
