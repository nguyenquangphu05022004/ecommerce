package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.domain.response.APIResponse;

public interface IEvaluationService {
    APIResponse<?> save(EvaluationRequest evaluationRequest);
    APIResponse<?> countByProductId(Long productId);
    APIResponse<?> countByVendorId(Long vendorId);
    APIResponse<?> delete(Long id);
}


