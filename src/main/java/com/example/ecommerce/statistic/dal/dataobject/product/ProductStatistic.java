package com.example.ecommerce.statistic.dal.dataobject.product;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
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
    private Integer browseCount;
    private Integer sold;

    private Integer numFavorite;
    private Integer numComment;

    private Integer avgRating;

    private Integer numOrdering;

    private Integer numCancelOrdering;

    @ManyToOne
    @JoinColumn(name = "product_spu_id", unique = true)
    private ProductSpu productSpu;


}
