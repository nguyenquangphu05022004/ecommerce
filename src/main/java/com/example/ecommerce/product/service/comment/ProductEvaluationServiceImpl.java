package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.product.controller.comment.evaluation.vo.ProductEvaluationReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentEvaluationRepository;
import com.example.ecommerce.product.dal.repository.comment.ProductEvaluationRepository;
import com.example.ecommerce.product.service.property.ProductPropertyService;
import com.example.ecommerce.product.service.spu.ProductSpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductEvaluationServiceImpl implements ProductEvaluationService{
    private final ProductEvaluationRepository productEvaluationRepository;
    private final ProductSpuService productSpuService;
    private final ProductPropertyService productPropertyService;
    @Override
    public ProductEvaluation createProductEvaluation(ProductEvaluationReqVO reqVO) {
        ProductEvaluation productEvaluation = ProductEvaluation.builder()
                .productProperty(productPropertyService.getById(reqVO.getPropertyId()))
                .productSpu(this.productSpuService.getProductSpuById(reqVO.getProductSpuId()))
                .build();

        this.productEvaluationRepository.save(productEvaluation);

        return productEvaluation;
    }

    @Override
    public List<ProductEvaluation> getAllProductEvaluationByProductSpuId(Long productSpuId) {
        return this.productEvaluationRepository.findAllByProductSpuId(productSpuId);
    }

    @Override
    public void deleteProductEvaluation(Long productEvaluationId) {

    }
}
