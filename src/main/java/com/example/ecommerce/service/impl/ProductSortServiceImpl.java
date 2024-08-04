package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.service.ProductSortService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductSortServiceImpl implements ProductSortService {

    @Override
    public List<Product> sortByPrice(List<Product> products) {
        return products.stream().sorted((p1, p2) -> p2.getPrice() - p1.getPrice())
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
            int sellerP1 = getTotalProductSold(p1);
            int sellerP2 = getTotalProductSold(p2);
            return sellerP2 - sellerP1;
        }).toList();
    }

    public static int getTotalProductSold(Product p1) {
        return p1.getProductInventory()
                .stream()
                .mapToInt(inventory -> inventory.getNumberOfProductSold()).sum();
    }

    @Override
    public List<Product> sortByDefault(List<Product> products) {
        return products.stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .toList();
    }
}
