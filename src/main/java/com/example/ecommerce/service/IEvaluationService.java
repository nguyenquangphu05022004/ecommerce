package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.domain.model.modelviews.evaluation.EvaluationDetailsModelView;

public interface IEvaluationService {
    EvaluationDetailsModelView save(EvaluationRequest evaluationRequest);
    long countByProductId(Long productId);
    long countByVendorId(Long vendorId);
    void delete(Long id);
}


