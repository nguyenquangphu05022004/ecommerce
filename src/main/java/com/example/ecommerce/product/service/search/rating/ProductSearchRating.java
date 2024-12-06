package com.example.ecommerce.product.service.search.rating;

import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.search.ProductSearchStrategy;
import jakarta.persistence.criteria.*;

public class ProductSearchRating implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        Double avgRating = Double.parseDouble(jsonData);
        /**
         * Query lay gia tri trung binh rating
         */
        Subquery<Double> subquery = builder.createQuery().subquery(Double.class);
        Root<ProductComment> productCommentRoot = subquery.from(ProductComment.class);
        subquery.select(builder.avg(productCommentRoot.get("rating")))
                .where(builder.equal(productCommentRoot.get("productSpu").get("id"), root.get("id")));

        return builder.greaterThanOrEqualTo(subquery, avgRating);
    }
}
