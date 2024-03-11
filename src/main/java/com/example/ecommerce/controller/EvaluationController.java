package com.example.ecommerce.controller;

import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.service.impl.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EvaluationController {

    private final EvaluationServiceImpl evaluationService;


    @Autowired
    public EvaluationController(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }
    @PostMapping("/evaluations")
    @ResponseBody
    public EvaluationDto createEvaluation(@RequestBody EvaluationDto evaluationDto) {
        return evaluationDto;
    }
}
