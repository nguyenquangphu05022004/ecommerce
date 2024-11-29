package com.example.ecommerce.production.dal.dataobject.comment;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "production_product_comment_evaluation")
public class ProductCommentEvaluation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_comment_id")
    private ProductComment productComment;
    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private ProductProperty productProperty;
    private String value;
}
