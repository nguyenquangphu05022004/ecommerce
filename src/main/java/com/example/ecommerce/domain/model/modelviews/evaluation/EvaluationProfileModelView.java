package com.example.ecommerce.domain.model.modelviews.evaluation;

import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class EvaluationProfileModelView extends EvaluationSimpleModelView{
    private ProductModelView product;

    public EvaluationProfileModelView(Evaluation e) {
        super(e);
        this.product = new ProductModelView(e.getProduct());
    }
}
