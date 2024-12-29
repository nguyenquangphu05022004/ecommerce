package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentResVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentEvaluation;
import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentFavorite;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentEvaluationRepository;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentFavoriteRepository;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentRepository;
import com.example.ecommerce.product.service.property.ProductPropertyService;
import com.example.ecommerce.product.service.sku.ProductSkuService;
import com.example.ecommerce.product.service.spu.ProductSpuService;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.frame.common.pojo.PagingLimitation.COMMENT_LIMIT;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PRODUCT_COMMENT_NOT_FOUND;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.UPDATE_COMMENT_IS_DENIED;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService{
    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentEvaluationRepository productCommentEvaluationRepository;
    private final ProductCommentFavoriteRepository productCommentFavoriteRepository;

    private final ProductSpuService productSpuService;
    private final ProductSkuService productSkuService;
    private final UserMemberService userMemberService;
    private final ProductPropertyService productPropertyService;
    @Override
    @Transactional
    public ProductComment createProductComment(ProductCommentCreateReqVO reqVO) {

        ProductComment productComment = ProductComment.builder()
                .productSku(this.productSkuService.getProductSkuById(reqVO.getProductSkuId()))
                .productSpu(this.productSpuService.getProductSpuById(reqVO.getProductSpuId()))
                .rating(reqVO.getRating())
                .userMember(userMemberService.getUserMemberById(reqVO.getUserMemberId()))
                .content(reqVO.getContent())
                .imageUrls(reqVO.getImageUrls()).build();

        this.productCommentRepository.save(productComment);
        if(!MapUtils.isEmpty(reqVO.getEvaluations())) {
            List<ProductCommentEvaluation> productCommentEvaluations = saveAllProductCommentEvaluation(reqVO, productComment);

            productComment.setProductCommentEvaluations(productCommentEvaluations);
        }
        return productComment;
    }
    private List<ProductCommentEvaluation> saveAllProductCommentEvaluation(ProductCommentCreateReqVO reqVO, ProductComment productComment) {
        List<ProductCommentEvaluation> productCommentEvaluations = convertList(reqVO.getEvaluations().entrySet(), entry -> {
            return ProductCommentEvaluation.builder().productComment(productComment)
                    .productProperty(productPropertyService.getById(entry.getKey()))
                    .propertyValue(entry.getValue()).build();
        });
        this.productCommentEvaluationRepository.saveAll(productCommentEvaluations);
        return productCommentEvaluations;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public ProductComment updateProductComment(ProductCommentCreateReqVO reqVO) {
        ProductComment productComment = getProductCommentById(reqVO.getId());
        if(!productComment.getUserMember().getId().equals(reqVO.getUserMemberId())) {
            throw exception(UPDATE_COMMENT_IS_DENIED);
        }
        if(!MapUtils.isEmpty(reqVO.getEvaluations())) {
            reqVO.getEvaluations().entrySet().forEach(entry -> {
                ProductCommentEvaluation productCommentEvaluation = this.productCommentEvaluationRepository
                        .findByProductCommentIdAndProductPropertyId(reqVO.getId(), entry.getKey())
                        .orElse(ProductCommentEvaluation.builder()
                                .productProperty(this.productPropertyService.getById(entry.getKey()))
                                .productComment(this.getProductCommentById(reqVO.getId())).build())
                        .toBuilder()
                        .propertyValue(entry.getValue())
                        .build();
                this.productCommentEvaluationRepository.save(productCommentEvaluation);
            });
        }
        productComment = productComment.toBuilder()
                .imageUrls(reqVO.getImageUrls()).content(reqVO.getContent())
                .rating(reqVO.getRating()).build();

        this.productCommentRepository.save(productComment);
        return productComment;
    }

    @Override
    public PageResult<ProductComment> getPageCommentByProductSpuId(PagingProductCommentReqVO reqVO) {
        Page<ProductComment> pageProductComment = this.productCommentRepository.findAllByProductSpuId(
                reqVO.getProductSpuId(),
                PageRequest.of(reqVO.getCurrentPage() - 1, COMMENT_LIMIT)
        );
        return new PageResult<>(pageProductComment);
    }

    @Override
    public PageResult<ProductComment> getPageProductCommentByUserMemberId(Long userMemberId, PageParam req) {
        return null;
    }

    @Override
    public ProductComment getProductCommentById(Long productCommentId) {
        return this.productCommentRepository.findById(productCommentId)
                .orElseThrow(() -> exception(PRODUCT_COMMENT_NOT_FOUND));
    }

    @Override
    @Transactional
    public int updateCommentLike(Long commentId, Long userMemberId) {
        Optional<ProductCommentFavorite> op = this.productCommentFavoriteRepository.findByProductCommentIdAndUserMemberId(
                commentId, userMemberId
        );
        if(op.isPresent()) {
            this.productCommentFavoriteRepository.delete(op.get());
        } else {
            productCommentFavoriteRepository.save(new ProductCommentFavorite(userMemberId, commentId));
        }
        return this.productCommentFavoriteRepository.countAllByProductCommentId(commentId);
    }

    @Transactional
    @Override
    public void delete(Long commentId) {
        this.productCommentEvaluationRepository.deleteAllByProductCommentId(commentId);
        this.productCommentRepository.deleteById(commentId);
    }

    @Override
    public PageResult<ProductComment> getPageCommentByProductSpuId(Long spuId, PageParam req) {
        Page<ProductComment> page = this.productCommentRepository.findAllByProductSpuId(spuId, req.buildPageRequest());
        return new PageResult<>(page);
    }

    @Override
    public boolean userHasLikeComment(Long userId, Long commentId) {
        return productCommentFavoriteRepository
                .findByProductCommentIdAndUserMemberId(commentId, userId)
                .isPresent();
    }
}
