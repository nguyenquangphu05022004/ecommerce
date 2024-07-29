package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.service.ProductSortService;

import java.util.List;
public class ProductSortServiceImpl implements ProductSortService {

    @Override
    public List<Product> sortByPrice(List<Product> products) {
        return products.stream().sorted((p1, p2) -> p2.getStocks().get(0).getPrice() - p1.getStocks().get(0).getPrice())
                .toList();
    }

    @Override
    public List<Product> sortByRateAverage(List<Product> products) {
        return null;
//        Collections.sort(products, (p1, p2) -> p2.getAverageRate() - p1.getAverageRate());
    }

    @Override
    public List<Product> sortByNumberOfSeller(List<Product> products) {
        return products.stream().sorted((p1, p2) ->
        {
            int sellerP1 = getTotalSeller(p1);
            int sellerP2 = getTotalSeller(p2);
            return sellerP2 - sellerP1;
        }).toList();
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
    public List<Product> sortByDefault(List<Product> products) {
        return products.stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .toList();
    }
}
