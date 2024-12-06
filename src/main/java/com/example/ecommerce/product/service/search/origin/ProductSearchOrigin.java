package com.example.ecommerce.product.service.search.origin;

import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.search.ProductSearchStrategy;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Tim kiem nguon goc san pham: Ha noi, Bac Ninh, Nuoc ngoai...
 */
public class ProductSearchOrigin implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        OriginDataSearch data = JsonUtils.parseObject(jsonData, new TypeReference<OriginDataSearch>() {});
        Path<Object> objectPath = root.get("productSpuDetails");
        Predicate equal1 = builder.equal(objectPath.get("productProperty").get("id"), data.getPropertyId());
        Predicate equal2 = builder.equal(objectPath.get("productPropertyValue").get("id"), data.getPropertyValueId());
        return builder.and(equal1, equal2);
    }
}
