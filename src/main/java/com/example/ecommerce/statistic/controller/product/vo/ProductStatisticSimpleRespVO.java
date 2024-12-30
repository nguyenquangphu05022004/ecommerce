package com.example.ecommerce.statistic.controller.product.vo;

import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import lombok.Data;

@Data
public class ProductStatisticSimpleRespVO {
    private Integer browseCount;
    private Integer sold;

    private Integer numFavorite;
    private Integer numComment;

    private Integer avgRating;

    private Integer numOrdering;

    private Integer numCancelOrdering;

    public ProductStatisticSimpleRespVO(ProductStatistic productStatistic) {
        this.browseCount = productStatistic.getBrowseCount();
        this.sold = productStatistic.getSold();
        this.numFavorite = productStatistic.getNumFavorite();
        this.numComment = productStatistic.getNumComment();
        this.avgRating = productStatistic.getAvgRating();
        this.numOrdering = productStatistic.getNumOrdering();
        this.numCancelOrdering = productStatistic.getNumCancelOrdering();
    }
}
