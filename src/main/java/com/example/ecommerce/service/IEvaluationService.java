package com.example.ecommerce.service;

import com.example.ecommerce.dto.EvaluationDto;

public interface IEvaluationService {
    EvaluationDto saveOrUpdate(EvaluationDto evaluationDto);

}
