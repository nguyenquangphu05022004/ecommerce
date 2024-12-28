package com.example.ecommerce.statistic.service;

import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import com.example.ecommerce.statistic.enums.OperationType;
import org.springframework.scheduling.annotation.Async;

public interface ProductStatisticService {
    @Async
    void doUpdateProductStatistic(Long productId, OperationType operationType, String fieldName);

    ProductStatistic getProductStatistic(Long spuId);

}
