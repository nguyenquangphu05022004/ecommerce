package com.example.ecommerce.product.controller.comment.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.*;

@Data
@Schema(name = "Product Comment Response")
public class ProductCommentResVO {
    private Long id;
    private LocalDateTime createdDate;
    private Double rating;

    @Schema(description = "Mo ta loai mat hang: Trang, 31")
    private List<String> productClassifications;

    @Schema(description = "Cac hang muc danh gia san pham: tinh nang noi bat: true, Chat luong san pham: rat la ok")
    private List<ProductCommentEvaluationResVO> productCommentEvaluations;
    private String content;

    private List<String> mediaUrls;

    private Integer numberOfLike;

    public ProductCommentResVO(ProductComment productComment) {
        this.id =productComment.getId();
        this.createdDate = productComment.getModifiedDate();
        this.rating = productComment.getRating();
        this.productClassifications = convertList(productComment.getProductSku().getProductSkuProperties(), property -> {
            return property.getProductPropertyValue().getValue();
        });
        this.productCommentEvaluations = convertList(productComment.getProductCommentEvaluations(), ProductCommentEvaluationResVO::new);
        this.mediaUrls = convertList(productComment.getMediaList(), f -> f.getPath());
        this.content = productComment.getContent();
        this.numberOfLike = CollUtils.size(productComment.getProductCommentFavorites());
    }
}
