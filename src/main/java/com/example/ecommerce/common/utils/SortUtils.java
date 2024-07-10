package com.example.ecommerce.common.utils;

import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.dto.SortProductType;
import com.example.ecommerce.service.ProductSortService;
import com.example.ecommerce.service.impl.ProductSortServiceImpl;

import java.util.List;

public class SortUtils {
    private static ProductSortService productSortService;
    public static void sortProduct(SortProductType type,
                                                    List<ProductDto> products) {
        if(productSortService == null) productSortService = new ProductSortServiceImpl();
        if(type.equals(SortProductType.PRICE))
             productSortService.sortByPrice(products);
        else if(type.equals(SortProductType.NUMBER_OF_SELLER))
             productSortService.sortByNumberOfSeller(products);
        else if(type.equals(SortProductType.RATE_AVERAGE))
             productSortService.sortByRateAverage(products);
        else
             productSortService.sortByDefault(products);
    }
}
