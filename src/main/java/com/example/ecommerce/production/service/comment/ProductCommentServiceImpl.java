package com.example.ecommerce.production.service.comment;

import com.example.ecommerce.file.FileEntity;
import com.example.ecommerce.file.FileStorageService;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.production.controller.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.production.controller.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.production.controller.comment.vo.ProductCommentUpdateReqVO;
import com.example.ecommerce.production.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.production.dal.dataobject.comment.ProductCommentEvaluation;
import com.example.ecommerce.production.dal.dataobject.comment.ProductCommentFavorite;
import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.dal.repository.comment.ProductCommentEvaluationRepository;
import com.example.ecommerce.production.dal.repository.comment.ProductCommentFavoriteRepository;
import com.example.ecommerce.production.dal.repository.comment.ProductCommentRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.ecommerce.file.Representation.COMMENT_IMAGE;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.frame.common.pojo.PagingLimitation.COMMENT_LIMIT;
import static com.example.ecommerce.frame.security.core.utils.SecurityUtils.getLoginUserMemberId;
import static com.example.ecommerce.production.enums.ProductionErrorConstant.PRODUCT_COMMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService{
    private final ProductCommentRepository productCommentRepository;
    private final FileStorageService fileStorageService;
    private final ProductCommentEvaluationRepository productCommentEvaluationRepository;
    private final ProductCommentFavoriteRepository productCommentFavoriteRepository;
    @Override
    @Transactional
    public ProductComment createProductComment(ProductCommentCreateReqVO reqVO, List<MultipartFile> files) {

        List<FileEntity> fileEntities = new ArrayList<>();
        if(!CollUtils.isEmpty(files)) {
               fileEntities.addAll(files.stream().map(f -> fileStorageService.save(f, COMMENT_IMAGE)).collect(Collectors.toList()));
        }
        ProductComment productComment = ProductComment.builder().productSku(ProductSku.builder().id(reqVO.getProductSkuId()).build())
                .productSpu(ProductSpu.builder().id(reqVO.getProductSpuId()).build()).content(reqVO.getContent())
                .rating(reqVO.getRating()).userMember(UserMember.builder().id(getLoginUserMemberId()).build())
                .mediaList(fileEntities).build();
        this.productCommentRepository.save(productComment);

        if(!MapUtils.isEmpty(reqVO.getMapProperties())) {
            List<ProductCommentEvaluation> productCommentEvaluations = reqVO.getMapProperties().entrySet().stream().map(property -> {
                ProductCommentEvaluation productEvaluation = ProductCommentEvaluation.builder().value(property.getValue())
                        .productProperty(ProductProperty.builder().id(property.getKey()).build())
                        .productComment(productComment).build();
                this.productCommentEvaluationRepository.save(productEvaluation);
                return productEvaluation;
            }).collect(Collectors.toList());
            productComment.setProductCommentEvaluations(productCommentEvaluations);
        }
        return productComment;
    }

    @Override
    @Transactional
    public ProductComment updateProductComment(ProductCommentUpdateReqVO reqVO, List<MultipartFile> files) {
        ProductComment productComment = getProductCommentById(reqVO.getId()).toBuilder()
                .content(reqVO.getContent()).rating(reqVO.getRating()).build();
        if(!CollUtils.isEmpty(productComment.getProductCommentEvaluations()) && !MapUtils.isEmpty(reqVO.getMapProperties())) {
            reqVO.getMapProperties().entrySet().forEach(pr -> {
                ProductCommentEvaluation productCommentEvaluation = productComment.getProductCommentEvaluations()
                        .stream().filter(e -> e.getProductProperty().getId().compareTo(pr.getKey()) == 0)
                        .findFirst().get();
                productCommentEvaluation.setValue(pr.getValue());
            });
            this.productCommentEvaluationRepository.saveAll(productComment.getProductCommentEvaluations());
        }
        this.productCommentRepository.save(productComment);
        return productComment;
    }

    @Override
    public PageResult<ProductComment> getListProductCommentByProductSpu(PagingProductCommentReqVO reqVO) {
        Page<ProductComment> pageProductComment = this.productCommentRepository.findAllByProductSpuId(
                reqVO.getProductSpuId(),
                PageRequest.of(reqVO.getCurrentPage() - 1, COMMENT_LIMIT)
        );
        return new PageResult<>(pageProductComment);
    }

    @Override
    public PageResult<ProductComment> getListProductCommentByUserMemberId(Long userMemberId) {
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
}
