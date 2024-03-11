package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.service.IGenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl  implements IGenericService<EvaluationDto> {

    @Override
    public List<EvaluationDto> records() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public EvaluationDto findById(Long id) {
        return null;
    }
}
