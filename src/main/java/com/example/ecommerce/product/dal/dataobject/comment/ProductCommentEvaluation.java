package com.example.ecommerce.product.dal.dataobject.comment;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "production_product_comment_evaluation")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class ProductCommentEvaluation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_comment_id")
    private ProductComment productComment;
    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private ProductProperty productProperty;
    private String propertyValue;
}
