package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.product.controller.comment.favorite.vo.ProductCommentFavoriteReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentFavorite;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentFavoriteRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCommentFavoriteServiceImpl implements ProductCommentFavoriteService{

    private final ProductCommentFavoriteRepository productCommentFavoriteRepository;

    @Override
    public Integer likeComment(ProductCommentFavoriteReqVO reqVO) {
        ProductCommentFavorite prCommentFavorite = ProductCommentFavorite.builder()
                .productComment(ProductComment.builder().id(reqVO.getCommentId()).build())
                .userMember(UserMember.builder().id(reqVO.getUserMemberId()).build())
                .build();
        this.productCommentFavoriteRepository.save(prCommentFavorite);

        return reqVO.getCurrentLike() + 1;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public Integer deleteCommentLike(ProductCommentFavoriteReqVO reqVO) {
        this.productCommentFavoriteRepository.deleteByUserMemberIdAndProductCommentId(
                reqVO.getUserMemberId(),
                reqVO.getCommentId()
        );
        return reqVO.getCurrentLike() - 1;
    }


    @Override
    public boolean userLikeComment(Long userMemberId, Long commentId) {
        return this.productCommentFavoriteRepository
                .findByProductCommentIdAndUserMemberId(commentId, userMemberId)
                .isPresent();
    }
}
