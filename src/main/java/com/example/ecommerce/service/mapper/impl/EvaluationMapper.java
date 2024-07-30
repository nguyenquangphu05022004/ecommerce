package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.service.dto.EvaluationDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.EvaluationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("evaluationMapper")
public class EvaluationMapper implements ImageMapper , IMapper<Evaluation, EvaluationRequest, EvaluationDto> {
    @Override
    public Evaluation toEntity(EvaluationRequest request) {
        var evaluation = Evaluation.builder()
                .user(User.builder().id(request.getUserId()).build())
                .content(request.getContent())
                .rating(request.getRating())
                .product(Product.builder().id(request.getProductId()).build())
                .build();
        return evaluation;
    }

    @Override
    public EvaluationDto toDto(Evaluation evaluation) {
        if(evaluation == null) return null;
        EvaluationDto response = EvaluationDto.builder()
                .rating(evaluation.getRating())
                .content(evaluation.getContent())
                .id(evaluation.getId())
                .imageUrls(getImageUrl(SystemUtils.TAG, evaluation.getImages()))
                .productId(evaluation.getProduct().getId())
                .userId(evaluation.getUser().getId())
                .modifiedDate(evaluation.getModifiedDate())
                .modifiedBy(evaluation.getModifiedBy())
                .build();
        return response;
    }

    @Override
    public List<EvaluationDto> toDtoList(Collection<? extends Evaluation> evaluations) {
        List<EvaluationDto> responses = new ArrayList<>();
        if(evaluations != null) {
            evaluations.forEach(eval -> responses.add(toDto(eval)));
        }
        return responses;
    }
}
