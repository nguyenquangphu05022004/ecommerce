package com.example.ecommerce.product.controller.comment.evaluation;

import com.example.ecommerce.product.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;
import lombok.Data;

@Data
public class ProductEvaluationResVO {
    private Long id;
    private ProductPropertyVO productProperty;

    public ProductEvaluationResVO(ProductEvaluation re) {
        this.id = re.getId();
        this.productProperty = new ProductPropertyVO(re.getProductProperty());
    }
}
