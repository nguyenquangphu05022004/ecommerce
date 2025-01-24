package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentResVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;

public interface ProductCommentService {
    /**
     * Create comment
     */
    ProductComment createProductComment(ProductCommentCreateReqVO reqVO);

    ProductComment updateProductComment(ProductCommentCreateReqVO reqVO);

    /**
     * get list comment by product
     * @param reqVO
     * @return
     */
    PageResult<ProductComment> getPageCommentByProductSpuId(PagingProductCommentReqVO reqVO);

    /**
     * get list comment of specific user
     *
     * @param userMemberId
     * @param req
     * @return
     */
    PageResult<ProductComment> getPageProductCommentByUserMemberId(Long userMemberId, PageParam req);

    /**
     * Valid product comment
     * @param productCommentId
     * @return
     */
    ProductComment getProductCommentById(Long productCommentId);
    int updateCommentLike(Long commentId, Long userMemberId);

    /**
     * Delete comment
     * @param commentId
     */
    void delete(Long commentId);

    PageResult<ProductComment> getPageCommentByProductSpuId(Long spuId, PageParam req);


    boolean userHasLikeComment(Long userId, Long commentId);
    boolean userHasComment(Long userId, Long productSpuId);
}
