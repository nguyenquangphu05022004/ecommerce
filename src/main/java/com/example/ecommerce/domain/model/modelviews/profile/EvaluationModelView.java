package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import lombok.Getter;

@Getter
public class EvaluationModelView {
    private String content;
    private int rating;
    private long id;
    private ProductModelView product;

    protected EvaluationModelView(Evaluation e) {
        this.content = e.getContent();
        this.rating = e.getRating();
        this.id = e.getId();
        this.product = new ProductModelView(e.getProduct());
    }
}
