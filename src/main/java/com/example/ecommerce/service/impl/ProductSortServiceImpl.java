package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.ProductSortService;
import com.example.ecommerce.service.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
public class ProductSortServiceImpl implements ProductSortService {

    @Override
    public void sortByPrice(List<Product> products) {
        Collections.sort(products, (p1, p2) -> p2.getStocks().get(0).getPrice() - p1.getStocks().get(0).getPrice());
    }

    @Override
    public void sortByRateAverage(List<Product> products) {
//        Collections.sort(products, (p1, p2) -> p2.getAverageRate() - p1.getAverageRate());
    }

    @Override
    public void sortByNumberOfSeller(List<Product> products) {
        Collections.sort(products, (p1, p2) ->
                {
                    int sellerP1 = getTotalSeller(p1);
                    int sellerP2 = getTotalSeller(p2);
                    return sellerP2 - sellerP1;
                }
        );
    }

    public static int getTotalSeller(Product p1) {
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
    public void sortByDefault(List<Product> products) {
        Collections.sort(products, (p1, p2) -> p1.getId().compareTo(p2.getId()));
    }
}
