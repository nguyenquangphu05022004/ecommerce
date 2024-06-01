package com.example.ecommerce.service;

import com.example.ecommerce.dto.DecorationDto;

import java.util.List;

public interface IDecorationService {
    void save(DecorationDto productTypeRequest);
    void update(Long id, DecorationDto productTypeRequest);
    void delete(Long id);
    List<DecorationDto> listDecoration();
}
