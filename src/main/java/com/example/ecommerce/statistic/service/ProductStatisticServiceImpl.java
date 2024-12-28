package com.example.ecommerce.statistic.service;

import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import com.example.ecommerce.statistic.dal.repo.product.ProductStatisticRepository;
import com.example.ecommerce.statistic.enums.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStatisticServiceImpl implements ProductStatisticService {
    private final ProductStatisticRepository productStatisticRepository;
    @Override
    public void doUpdateProductStatistic(Long productId,
                                         OperationType operationType, String fieldName) {
        ProductStatistic productStatistic = getBySpuId(productId);
//        ObjectUtils.setField(productStatistic, FieldNameAnnotation.class, 1);
    }

    @Override
    public ProductStatistic getProductStatistic(Long spuId) {
        return getBySpuId(spuId);
    }

    private ProductStatistic getBySpuId(Long id) {
        ProductStatistic productStatistic = productStatisticRepository.findByProductSpuId(id)
                .orElse(null);
        if(productStatistic == null) {
            productStatistic = new ProductStatistic();
            productStatistic.setAvgRating(0);productStatistic.setSold(0);
            productStatistic.setBrowseCount(0);productStatistic.setNumFavorite(0);
            productStatistic.setNumOrdering(0); productStatistic.setNumCancelOrdering(0);
            productStatistic.setNumComment(0);
            this.productStatisticRepository.save(productStatistic);
        }
        return productStatistic;
    }
}
