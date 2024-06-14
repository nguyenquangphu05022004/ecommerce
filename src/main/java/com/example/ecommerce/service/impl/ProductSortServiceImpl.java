package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.service.ProductSortService;

import java.util.Collections;
import java.util.List;

public class ProductSortServiceImpl implements ProductSortService {

    @Override
    public List<ProductDto> sortByPrice(List<ProductDto> products) {
        Collections.sort(products, (ProductDto p1, ProductDto p2) -> {
            return p2.getStocks().get(0).getPrice() - p1.getStocks().get(0).getPrice();
        });
        return products;
    }

    @Override
    public List<ProductDto> sortByRateAverage(List<ProductDto> products) {
        Collections.sort(products, (ProductDto p1, ProductDto p2) -> {
            return p2.getAverageEvaluation() - p1.getAverageEvaluation();
        });
        return products;
    }

    @Override
    public List<ProductDto> sortByNumberOfSeller(List<ProductDto> products) {
        Collections.sort(products, (ProductDto p1, ProductDto p2) -> {
            return p2.getProductSeller().getNumberOfProductsSold() - p1.getProductSeller().getNumberOfProductsSold();
        });
        return products;
    }

    @Override
    public List<ProductDto> sortByDefault(List<ProductDto> products) {
        Collections.sort(products, (ProductDto p1, ProductDto p2) -> {
            return p1.getId().compareTo(p2.getId());
        });
        return products;
    }
}
