package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.entity.Evaluation;

public class EvaluationConverter implements IGenericConverter<Evaluation, EvaluationDto> {
    @Override
    public Evaluation toEntity(EvaluationDto evaluationDto) {
        return null;
    }

    @Override
    public EvaluationDto toDto(Evaluation evaluation) {
        return null;
    }

    @Override
    public Evaluation toEntity(Evaluation evaluation, EvaluationDto evaluationDto) {
        return null;
    }
}
