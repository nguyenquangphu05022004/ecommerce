package com.example.ecommerce.product.service.search.category;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.search.ProductSearchStrategy;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ProductSearchCategory implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        Set<Integer> set = new HashSet<>(
                JsonUtils.parseObject(jsonData, new TypeReference<Collection<? extends Integer>>() {})
        );
        if(CollUtils.isEmpty(set)) {
            return builder.conjunction();
        }
        return builder.in(root.get("productCategory").get("id")).value(set);
    }

}
