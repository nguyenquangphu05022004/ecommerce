package com.example.ecommerce.production.service.comment;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.production.controller.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.production.controller.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.production.controller.comment.vo.ProductCommentUpdateReqVO;
import com.example.ecommerce.production.dal.dataobject.comment.ProductComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCommentService {
    /**
     * Only user who received the product can comment
     * @param reqVO: info for creating comment
     * @param files: show product image/video
     * @return: Comment was created successfully
     */
    ProductComment createProductComment(ProductCommentCreateReqVO reqVO, List<MultipartFile> files);
    ProductComment updateProductComment(ProductCommentUpdateReqVO reqVO, List<MultipartFile> files);

    PageResult<ProductComment> getListProductCommentByProductSpu(PagingProductCommentReqVO reqVO);
    PageResult<ProductComment> getListProductCommentByUserMemberId(Long userMemberId);

    ProductComment getProductCommentById(Long productCommentId);

    int updateCommentLike(Long commentId, Long userMemberId);

    void delete(Long commentId);

}
