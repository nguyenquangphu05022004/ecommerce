package com.example.ecommerce.product.dal.dataobject.comment;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Setter
@Table(name = "production_comment_product_evaluation")
public class ProductEvaluation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;
    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private ProductProperty productProperty;
}
