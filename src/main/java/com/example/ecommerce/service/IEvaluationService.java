package com.example.ecommerce.service;

import com.example.ecommerce.dto.EvaluationDto;

import java.util.List;

public interface IEvaluationService {
    EvaluationDto saveOrUpdate(EvaluationDto evaluationDto);
    long count();
    void delete(Long id);

}
