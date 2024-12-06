package com.example.ecommerce.product.service.search.price;

import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.search.ProductSearchStrategy;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSearchPrice implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        PriceDataSearchVO priceDataSearchVO = JsonUtils.parseObject(jsonData, new TypeReference<PriceDataSearchVO>() {});
        return builder.between(root.get("productSkus").get("price"), priceDataSearchVO.getFromPrice(), priceDataSearchVO.getToPrice());
    }
}
