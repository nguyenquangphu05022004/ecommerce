package com.example.ecommerce.converter;

import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.EvaluationRequest;
import com.example.ecommerce.domain.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationConverter {
    private final ModelMapper modelMapper;

    public Evaluation mapToEntity(EvaluationRequest evaluationRequest) {
        Evaluation evaluation = modelMapper.map(
                        evaluationRequest,
                        Evaluation.class)
                .toBuilder()
                .product(Product.builder().id(evaluationRequest.getProductId())
                        .build())
                .build();
        return evaluation;
    }

    public EvaluationDto mapToResponse(Evaluation evaluation) {
        EvaluationDto evaluationDto = modelMapper.map(evaluation, EvaluationDto.class);
        return evaluationDto;
    }
}
