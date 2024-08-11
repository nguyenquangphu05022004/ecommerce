package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.domain.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/products/evaluations")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class EvaluationController {
    private final IEvaluationService evaluationService;

    @PostMapping
    public APIResponse<?> createEvaluation(
            @RequestPart("evaluationRequest") EvaluationRequest request,
            @RequestParam(value = "files", required = false) List<MultipartFile> files
    ) {
        request.setFiles(files);
        return evaluationService.save(request);
    }

    @DeleteMapping("/{id}")
    public APIResponse<?> deleteById(@PathVariable("id") Long id) {
        return evaluationService.delete(id);
    }
}
