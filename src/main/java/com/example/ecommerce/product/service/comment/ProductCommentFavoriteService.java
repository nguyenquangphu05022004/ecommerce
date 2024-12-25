package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.product.controller.admin.comment.favorite.vo.ProductCommentFavoriteReqVO;

public interface ProductCommentFavoriteService {
    Integer likeComment(ProductCommentFavoriteReqVO reqVO);
    Integer deleteCommentLike(ProductCommentFavoriteReqVO reqVO);

    /**
     * @param userMemberId
     * @param commentId
     * @return: true if user reviewed else false
     */
    boolean userLikeComment(Long userMemberId, Long commentId);
}
