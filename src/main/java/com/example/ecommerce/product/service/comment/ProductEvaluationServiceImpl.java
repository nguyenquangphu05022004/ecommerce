package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.product.constants.ProductionErrorConstant;
import com.example.ecommerce.product.controller.admin.comment.evaluation.vo.ProductEvaluationReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;
import com.example.ecommerce.product.dal.repository.comment.ProductEvaluationRepository;
import com.example.ecommerce.product.service.property.ProductPropertyService;
import com.example.ecommerce.product.service.spu.ProductSpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

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
    public ProductEvaluation getEvaluationById(Long productEvaluationId) {
        return this.productEvaluationRepository.findById(productEvaluationId)
                .orElseThrow(() -> exception(ProductionErrorConstant.PRODUCT_EVALUATION_NOT_FOUND));
    }

    @Override
    public ProductEvaluation updateEvaluation(ProductEvaluationReqVO req) {
        ProductEvaluation evaluation = getEvaluationById(req.getId())
                .toBuilder().productProperty(productPropertyService.getById(req.getPropertyId()))
                .productSpu(productSpuService.getProductSpuById(req.getProductSpuId()))
                .build();
        this.productEvaluationRepository.save(evaluation);
        return evaluation;
    }
}
