package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.ProductDto;

import java.util.List;

public interface ProductSortService {
    List<ProductDto> sortByPrice(List<ProductDto> products);
    List<ProductDto> sortByRateAverage(List<ProductDto> products);
    List<ProductDto> sortByNumberOfSeller(List<ProductDto> products);
    List<ProductDto> sortByDefault(List<ProductDto> products);

}
