package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.Product;
import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.domain.model.modelviews.evaluation.EvaluationDetailsModelView;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.domain.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ecommerce.domain.entities.EntityType.Type.EVALUATION;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final IFilesStorageService filesStorageService;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public APIResponse<?> save(EvaluationRequest request) {
        Evaluation evaluation = Evaluation.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .product(Product.builder().id(request.getProductId()).build())
                .parent(request.getEvalParentId() != null ? Evaluation.builder().id(request.getEvalParentId()).build() : null)
                .user(userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).orElseThrow(() -> new UsernameNotFoundException("You aren't login")))
                .build();
        Evaluation saved = evaluationRepository.save(evaluation);
        if(request.getFiles() != null) {
            saved.setImages(request.getFiles()
                    .stream()
                    .map(s -> filesStorageService.saveFile(s, new EntityType(EVALUATION, saved.getId())))
                    .toList());
        }
        return apiResponse("created evaluation", new EvaluationDetailsModelView(saved));
    }

    @Override
    public APIResponse<?> countByProductId(Long productId) {
        return apiResponse(
                "number of evaluation of product",
                evaluationRepository.countByProductId(productId));
    }

    @Override
    public APIResponse<?> countByVendorId(Long vendorId) {
        return apiResponse(
                "number of evaluation of vendor",
                1
        );
    }

    @Override
    public APIResponse<?> delete(Long id) {
        evaluationRepository.deleteById(id);
        return apiResponse(
                SecurityUtils.getUsername() + " deleted evaluation",
                null
        );
    }
}
