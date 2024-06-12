package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.EvaluationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEvaluationService {
    EvaluationDto saveOrUpdate(EvaluationRequest evaluationRequest,
                               List<MultipartFile> images);
    long count();
    void delete(Long id);


}
