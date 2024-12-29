package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.product.controller.admin.comment.evaluation.vo.ProductEvaluationReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;

import java.util.List;

public interface ProductEvaluationService {
    ProductEvaluation createProductEvaluation(ProductEvaluationReqVO reqVO);
    List<ProductEvaluation> getAllProductEvaluationByProductSpuId(Long productSpuId);

    ProductEvaluation getEvaluationById(Long productEvaluationId);

    ProductEvaluation updateEvaluation(ProductEvaluationReqVO req);
}
