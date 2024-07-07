package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.Evaluation;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.service.dto.EvaluationDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.EvaluationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final IImageService imageService;
    @Qualifier("evaluationMapper")
    private final IMapper<Evaluation, EvaluationRequest, EvaluationDto> mapper;
    @Override
    @Transactional
    public void save(EvaluationRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getContent(), "Evaluation Content");
        ValidationUtils.fieldCheckNullOrEmpty(request.getRating(), "Evaluation Rating");
        ValidationUtils.fieldCheckNullOrEmpty(request.getProductId(), "Evaluation ProductID");
        Evaluation evaluation = mapper.toEntity(request);
        if(evaluation.getId() != null) {
            var old = evaluationRepository.findById(evaluation.getId()).get();
            evaluation = mapper.toEntity(request, old);
        }
        evaluationRepository.save(evaluation);
    }

    @Override
    public long count() {
        return evaluationRepository.count();
    }

    @Override
    public void delete(Long id) {
        evaluationRepository.deleteById(id);
    }
}
