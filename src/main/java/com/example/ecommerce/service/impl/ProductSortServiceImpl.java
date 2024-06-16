package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.service.ProductSortService;

import java.util.Collections;
import java.util.List;

public class ProductSortServiceImpl implements ProductSortService {

    @Override
    public List<ProductDto> sortByPrice(List<ProductDto> products) {
        Collections.sort(products, (p1, p2) -> p2.getStocks().get(0).getPrice() - p1.getStocks().get(0).getPrice());
        return products;
    }

    @Override
    public List<ProductDto> sortByRateAverage(List<ProductDto> products) {
        Collections.sort(products, (p1, p2) -> p2.getAverageEvaluation() - p1.getAverageEvaluation());
        return products;
    }

    @Override
    public List<ProductDto> sortByNumberOfSeller(List<ProductDto> products) {
        Collections.sort(products, (p1, p2) ->
            p2.getNumberOfProductSold() - p1.getNumberOfProductSold()
        );
        return products;
    }

    @Override
    public List<ProductDto> sortByDefault(List<ProductDto> products) {
        Collections.sort(products, (p1, p2) -> p1.getId().compareTo(p2.getId()));
        return products;
    }
}
