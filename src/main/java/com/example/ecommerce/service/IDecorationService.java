package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.DecorationResponse;

import java.util.List;

public interface IDecorationService {
    void save(DecorationResponse productTypeRequest);
    void update(Long id, DecorationResponse productTypeRequest);
    void delete(Long id);
    List<DecorationResponse> listDecoration();
}
