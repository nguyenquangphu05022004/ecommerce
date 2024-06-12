package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.EvaluationRequest;
import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.service.impl.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EvaluationController {

    private final EvaluationServiceImpl evaluationService;


    @Autowired
    public EvaluationController(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }

    //    @PostMapping("/evaluations")
//    @ResponseBody
//    public EvaluationDto createEvaluation(@RequestBody EvaluationDto evaluationDto) {
//        return evaluationService.saveOrUpdate(evaluationDto);
//    }
    @PostMapping("/evaluations")
    public String createEvaluation(@ModelAttribute EvaluationRequest evaluationRequest,
                                   @RequestParam("image") List<MultipartFile> images) {
        evaluationService.saveOrUpdate(evaluationRequest, images);
        return "redirect:/products/" + evaluationRequest.getProductId();
    }
}
