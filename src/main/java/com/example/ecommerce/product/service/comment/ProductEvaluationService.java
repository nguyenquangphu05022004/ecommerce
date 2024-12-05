package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.product.controller.comment.evaluation.vo.ProductEvaluationReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;

import java.util.List;
import java.util.stream.Collectors;

public interface ProductEvaluationService {
    ProductEvaluation createProductEvaluation(ProductEvaluationReqVO reqVO);
    List<ProductEvaluation> getAllProductEvaluationByProductSpuId(Long productSpuId);

    void deleteProductEvaluation(Long productEvaluationId);
}
