package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.EvaluationDto;
import com.example.ecommerce.domain.dto.EvaluationRequest;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.impl.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EvaluationController {

    private final IEvaluationService evaluationService;


    @Autowired
    public EvaluationController(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluations")
    public String createEvaluation(@ModelAttribute EvaluationRequest evaluationRequest) {
        EvaluationDto eval = evaluationService.saveOrUpdate(evaluationRequest);
        return "redirect:/products/" + evaluationRequest.getProductId() + "#" + eval.getId();
    }
}
