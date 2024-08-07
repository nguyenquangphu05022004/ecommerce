package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.domain.model.modelviews.evaluation.EvaluationDetailsModelView;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final IFilesStorageService filesStorageService;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public EvaluationDetailsModelView save(EvaluationRequest request) {
        Evaluation evaluation = Evaluation.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .product(Product.builder().id(request.getProductId()).build())
                .parent(request.getEvalParentId() != null ? Evaluation.builder().id(request.getEvalParentId()).build() : null)
                .user(userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).orElseThrow(() -> new UsernameNotFoundException("You aren't login")))
                .build();
        Evaluation saved = evaluationRepository.save(evaluation);
        if(request.getFiles() != null) {
            List<Evaluation.EvaluationImage> files = request.getFiles()
                    .stream().map(file -> (Evaluation.EvaluationImage) filesStorageService.saveFile(
                            file,
                            saved.getId(),
                            FileEntityType.EVALUATION
                    ))
                    .toList();
            saved.setImages(files);
        }
        return new EvaluationDetailsModelView(saved);
    }

    @Override
    public long countByProductId(Long productId) {
        return evaluationRepository.countByProductId(productId);
    }

    @Override
    public long countByVendorId(Long vendorId) {
        return 0;
    }

    @Override
    public void delete(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Not found"));
        evaluation.getImages().forEach(f -> filesStorageService.deleteImage(f));
        evaluationRepository.delete(evaluation);
    }
}
