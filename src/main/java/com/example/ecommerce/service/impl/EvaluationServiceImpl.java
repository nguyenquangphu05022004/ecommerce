package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.dto.EvaluationDto;
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
    private final IFilesStorageService filesStorageService;
    @Override
    @Transactional
    public void save(EvaluationRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getContent(), "Evaluation Content");
        ValidationUtils.fieldCheckNullOrEmpty(request.getRating(), "Evaluation Rating");
        ValidationUtils.fieldCheckNullOrEmpty(request.getProductId(), "Evaluation ProductID");
        Evaluation evaluation = null;
        if(evaluation.getId() != null) {
            var old = evaluationRepository
                    .findById(evaluation.getId())
                    .orElseThrow(() -> new GeneralException("Evaluation not found with id: " + request.getId()));
            filesStorageService.deleteImage(old.getImages());
        }
        Evaluation saved = evaluationRepository.save(evaluation);
        request.getFileImages()
                .forEach(file -> filesStorageService.saveFile(
                        file,
                        saved.getId(),
                        FileEntityType.EVALUATION
                ));
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
