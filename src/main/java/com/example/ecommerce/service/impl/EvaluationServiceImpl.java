package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final IImageService imageService;
    private final IFilesStorageService filesStorageService;


    @Override
    @Transactional
    public EvaluationDto saveOrUpdate(EvaluationDto evaluationDto, List<MultipartFile> images) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();

        Optional<Evaluation> optionalEvaluation = evaluationRepository
                .findByUserUsernameAndProductId(
                        SecurityUtils.username(),
                        evaluationDto.getProduct().getId()
                );

        Evaluation evaluation = null;
        if (optionalEvaluation.isPresent()) {
            evaluation = optionalEvaluation.get();
            List<Image> imageOfEvaluation = evaluation.getImages() == null
                    ? new ArrayList<>() : evaluation.getImages();

            imageOfEvaluation.stream().forEach(image -> filesStorageService
                    .deleteFile(image.getName())
            );
            evaluation = (Evaluation) Convert.EVAL.toEntity(evaluation, evaluationDto);
        } else {
            evaluation = (Evaluation) Convert.EVAL.toEntity(evaluationDto);
        }
        evaluation.setUser(user);
        evaluation = evaluationRepository.save(evaluation);
        for (MultipartFile mul : images) {
            if (mul != null) {
                imageService.uploadFile(mul, SystemUtils.SHORT_URL_EVALUATION);
            }
        }
        return (EvaluationDto) Convert.EVAL.toDto(evaluation);
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
