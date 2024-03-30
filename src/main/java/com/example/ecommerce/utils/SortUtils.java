package com.example.ecommerce.utils;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.SortProductType;
import com.example.ecommerce.service.ProductSortService;
import com.example.ecommerce.service.impl.ProductSortServiceImpl;

import java.util.List;

public class SortUtils {

    private static ProductSortService productSortService;

    public static List<ProductDto> getListAfterSort(SortProductType type,
                                                    List<ProductDto> products) {
        if(productSortService == null) productSortService = new ProductSortServiceImpl();
        if(type.equals(SortProductType.PRICE))
            return productSortService.sortByPrice(products);
        else if(type.equals(SortProductType.NUMBER_OF_SELLER))
            return productSortService.sortByNumberOfSeller(products);
        else if(type.equals(SortProductType.RATE_AVERAGE))
            return productSortService.sortByRateAverage(products);
        else
            return productSortService.sortByDefault(products);
    }
}
