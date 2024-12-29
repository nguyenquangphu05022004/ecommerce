package com.example.ecommerce.statistic.dal.dataobject.product;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.statistic.enums.FieldNameAnnotation;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "statistic_products")
@Entity
@Getter
@Setter
public class ProductStatistic extends BaseEntity {
    @FieldNameAnnotation(name = "browse")
    private Integer browseCount;
    @FieldNameAnnotation(name = "sold")
    private Integer sold;

    @FieldNameAnnotation(name = "favorite")
    private Integer numFavorite;
    @FieldNameAnnotation(name = "comment")
    private Integer numComment;

    @FieldNameAnnotation(name = "avg_rating")
    private Integer avgRating;

    @FieldNameAnnotation(name = "order")
    private Integer numOrdering;

    @FieldNameAnnotation(name = "cancel_order")
    private Integer numCancelOrdering;

    @ManyToOne
    @JoinColumn(name = "product_spu_id", unique = true)
    private ProductSpu productSpu;


}
