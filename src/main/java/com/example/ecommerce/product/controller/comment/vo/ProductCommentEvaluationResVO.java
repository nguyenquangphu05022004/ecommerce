package com.example.ecommerce.product.controller.comment.vo;

import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentEvaluation;
import lombok.Data;

@Data
public class ProductCommentEvaluationResVO {
    private String property;
    private String propertyValue;
    public ProductCommentEvaluationResVO(ProductCommentEvaluation productCommentEvaluation) {
        this.property = productCommentEvaluation.getProductProperty().getName();
        this.propertyValue = productCommentEvaluation.getPropertyValue();
    }
}
