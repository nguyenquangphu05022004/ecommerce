package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.dto.ImageDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.UserContactDetails;
import org.modelmapper.ModelMapper;

public class EvaluationConverter implements IGenericConverter<Evaluation, EvaluationDto> {
    private ModelMapper modelMapper;

    public EvaluationConverter(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @Override
    public Evaluation toEntity(EvaluationDto evaluationDto) {
        return Evaluation.builder().numberOfLike(0)
                .content(evaluationDto.getContent()).rating(evaluationDto.getRating())
                .product(Product.builder().id(evaluationDto.getId()).build()).build();
    }

    @Override
    public EvaluationDto toDto(Evaluation evaluation) {
        User user = evaluation.getUser();
        evaluation = evaluation.toBuilder()
                .user(null).product(null).build();
        EvaluationDto evaluationDto = modelMapper.map(evaluation, EvaluationDto.class);
        evaluationDto.setUser(UserDto.builder()
                .userContactDetails(user.getUserContactDetails())
                .avatar(new ImageDto(user.getAvatar().getName(), user.getAvatar().getShortUrl()))
                .build());
        return evaluationDto;
    }

    @Override
    public Evaluation toEntity(Evaluation evaluation, EvaluationDto evaluationDto) {
        return evaluation.toBuilder()
                .rating(evaluationDto.getRating())
                .content(evaluationDto.getContent())
                .build();
    }
}
