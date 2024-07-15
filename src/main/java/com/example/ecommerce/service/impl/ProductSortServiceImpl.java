package com.example.ecommerce.service.impl;

import com.example.ecommerce.service.ProductSortService;
import com.example.ecommerce.service.dto.ProductDto;
import org.springframework.stereotype.Service;

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
        Collections.sort(products, (p1, p2) -> p2.getAverageRate() - p1.getAverageRate());
        return products;
    }

    @Override
    public List<ProductDto> sortByNumberOfSeller(List<ProductDto> products) {
        Collections.sort(products, (p1, p2) ->
                {
                    int sellerP1 = getTotalSeller(p1);
                    int sellerP2 = getTotalSeller(p2);
                    return sellerP2 - sellerP1;
                }
        );
        return products;
    }

    public static int getTotalSeller(ProductDto p1) {
        return p1.getStocks()
                .stream()
                .mapToInt(stock -> {
                    return stock.getStockClassifications()
                            .stream()
                            .mapToInt(stockClassification -> stockClassification.getSeller())
                            .sum();
                }).sum();
    }

    @Override
    public List<ProductDto> sortByDefault(List<ProductDto> products) {
        Collections.sort(products, (p1, p2) -> p1.getId().compareTo(p2.getId()));
        return products;
    }
}
