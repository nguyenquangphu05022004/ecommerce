package com.example.ecommerce.service;

import com.example.ecommerce.dto.EvaluationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEvaluationService {
    EvaluationDto saveOrUpdate(EvaluationDto evaluationDto, List<MultipartFile> images);
    long count();
    void delete(Long id);


}
