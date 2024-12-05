package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.product.controller.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.product.controller.comment.vo.ProductCommentUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCommentService {
    /**
     * Create comment
     */
    ProductComment createProductComment(ProductCommentCreateReqVO reqVO, List<MultipartFile> files);

    /**
     * Update comment
     * @param reqVO
     * @param files
     * @return
     */
    ProductComment updateProductComment(ProductCommentUpdateReqVO reqVO, List<MultipartFile> files);

    /**
     * get list comment by product
     * @param reqVO
     * @return
     */
    PageResult<ProductComment> getListProductCommentByProductSpu(PagingProductCommentReqVO reqVO);

    /**
     * get list comment of specific user
     * @param userMemberId
     * @return
     */
    PageResult<ProductComment> getListProductCommentByUserMemberId(Long userMemberId);

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

}
