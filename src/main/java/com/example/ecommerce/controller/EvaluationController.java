package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createEvaluation(
            @RequestPart("evaluationRequest") EvaluationRequest request,
            @RequestParam(value = "files", required = false) List<MultipartFile> files
    ) {
        request.setFiles(files);
        return ResponseEntity.ok(evaluationService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        evaluationService.delete(id);
        return ResponseEntity.ok(
                new OperationResponse(
                        true,
                        "delete success",
                        200
                )
        );
    }
}
