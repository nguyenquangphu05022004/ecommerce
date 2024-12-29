package com.example.ecommerce.product.controller.app.comment.vo;

import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentResVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppProductCommentRespVO extends ProductCommentResVO {
    private Boolean currentUserHasLike = false;
    public AppProductCommentRespVO(ProductComment productComment) {
        super(productComment);
    }
    public AppProductCommentRespVO(ProductComment productComment, Boolean currentUserHasLike) {
        this(productComment);
        this.currentUserHasLike = currentUserHasLike;
    }
}
