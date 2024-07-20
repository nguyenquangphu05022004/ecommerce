package com.example.ecommerce.service;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.dto.ProductDto;

import java.util.List;

public interface ProductSortService {
    List<Product> sortByPrice(List<Product> products);
    List<Product> sortByRateAverage(List<Product> products);
    List<Product> sortByNumberOfSeller(List<Product> products);
    List<Product> sortByDefault(List<Product> products);
}
