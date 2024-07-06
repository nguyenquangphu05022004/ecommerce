package com.example.ecommerce.service;


import com.example.ecommerce.service.request.EvaluationRequest;

public interface IEvaluationService {
    void createEvaluation(EvaluationRequest evaluationRequest);
    long count();
    void delete(Long id);
}


