package com.example.ecommerce.production.controller.comment.vo;

import com.example.ecommerce.production.dal.dataobject.comment.ProductCommentEvaluation;
import lombok.Data;

@Data
public class ProductCommentEvaluationResVO {
    private String property;
    private String propertyValue;
    public ProductCommentEvaluationResVO(ProductCommentEvaluation productCommentEvaluation) {
        this.property = productCommentEvaluation.getProductProperty().getName();
        this.propertyValue = productCommentEvaluation.getValue();
    }
}
