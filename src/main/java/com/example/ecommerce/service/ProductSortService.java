package com.example.ecommerce.service;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.dto.ProductDto;

import java.util.List;

public interface ProductSortService {
    void sortByPrice(List<Product> products);
    void sortByRateAverage(List<Product> products);
    void sortByNumberOfSeller(List<Product> products);
    void sortByDefault(List<Product> products);
}
