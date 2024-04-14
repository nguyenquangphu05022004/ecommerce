package com.example.ecommerce.controller;

import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.service.impl.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @PostMapping("/evaluations/{productId}")
    public String createEvaluation(@PathVariable("productId") Long productId,
                                          @ModelAttribute EvaluationDto evaluationDto,
                                          @RequestParam("image") List<MultipartFile> images) {
        evaluationDto = evaluationDto.toBuilder().product(ProductDto.builder().id(productId).build()).build();
        evaluationService.saveOrUpdate(evaluationDto, images.get(0).getOriginalFilename().isEmpty() ? new ArrayList<>() : images);
        return "redirect:/products/" + productId;
    }
}
