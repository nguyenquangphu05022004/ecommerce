package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Evaluation;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.EvaluationRequest;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final IImageService imageService;
    private final ModelMapper mapper;
    @Override
    @Transactional
    public EvaluationDto saveOrUpdate(EvaluationRequest request,
                                      List<MultipartFile> images) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        Optional<Evaluation> optionalEvaluation = evaluationRepository
                .findByUserUsernameAndProductId(
                        SecurityUtils.username(),
                        request.getProductId()
                );
        Evaluation evaluation = null;
        if (optionalEvaluation.isPresent()) {
            evaluation = optionalEvaluation.get().toBuilder()
                    .rating(request.getRating())
                    .content(request.getContent().isEmpty()
                            || request.getContent().isBlank()
                            ? evaluation.getContent()
                            : request.getContent())
                    .build();
            evaluation.getImages().stream().forEach(
                    image -> imageService.deleteFile(image.getName(), image.getId())
            );

        } else {
            evaluation = mapper.map(request, Evaluation.class);
        }
        evaluation.setUser(user);
        List<Image> imageEvaluations = images.stream().map(
                file -> imageService.uploadFile(file, SystemUtils.TAG)
        ).collect(Collectors.toList());
        evaluation.setImages(imageEvaluations);
        evaluation = evaluationRepository.save(evaluation);
        return mapper.map(evaluation, EvaluationDto.class);
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
