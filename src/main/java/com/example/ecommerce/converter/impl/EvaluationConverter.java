package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.UserContactDetails;
import org.modelmapper.ModelMapper;

public class EvaluationConverter implements IGenericConverter<Evaluation, EvaluationDto> {
    private ModelMapper modelMapper;

    public EvaluationConverter(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @Override
    public Evaluation toEntity(EvaluationDto evaluationDto) {
        Evaluation evaluation = modelMapper.map(evaluationDto, Evaluation.class);
        return evaluation;
    }

    @Override
    public EvaluationDto toDto(Evaluation evaluation) {
        UserContactDetails userContactDetails = evaluation.getUser().getUserContactDetails();
        evaluation = evaluation.toBuilder()
                .user(null).product(null).build();
        EvaluationDto evaluationDto = modelMapper.map(evaluation, EvaluationDto.class);
        evaluationDto.setUser(UserDto.builder()
                .userContactDetails(userContactDetails)
                .build());
        return evaluationDto;
    }

    @Override
    public Evaluation toEntity(Evaluation evaluation, EvaluationDto evaluationDto) {
        return null;
    }
}
