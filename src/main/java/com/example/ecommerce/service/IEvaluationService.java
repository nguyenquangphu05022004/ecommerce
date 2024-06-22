package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.EvaluationDto;
import com.example.ecommerce.domain.dto.EvaluationRequest;

public interface IEvaluationService {
    EvaluationDto saveOrUpdate(EvaluationRequest evaluationRequest);
    long count();
    void delete(Long id);


}
