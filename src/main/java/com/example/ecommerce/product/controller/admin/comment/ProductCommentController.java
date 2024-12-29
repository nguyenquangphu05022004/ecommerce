package com.example.ecommerce.product.controller.admin.comment;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.product.controller.admin.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentResVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.service.comment.ProductCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "ADMIN_COMMENT")
@RequestMapping("/admin-api/product/comments")
public class ProductCommentController {
    private final ProductCommentService productCommentService;

    @PostMapping
    @Operation(summary = "Comment about specific product spu")
    public CommonResult<ProductCommentResVO> createProductComment(@RequestBody ProductCommentCreateReqVO reqVO) {
        reqVO.setUserMemberId(SecurityUtils.getLoginUserMemberId());
        return CommonResult.success(productCommentService.createProductComment(reqVO), ProductCommentResVO::new);
    }

    @GetMapping("/product-spu")
    @Operation(summary = "Get list comment of specific product spu")
    public CommonResult<PageResult<ProductCommentResVO>> getListProductCommentByProductSpu(@RequestBody PagingProductCommentReqVO reqVO) {
        PageResult<ProductComment> pageResult = this.productCommentService.getPageCommentByProductSpuId(reqVO);
        return CommonResult.success(pageResult, ProductCommentResVO::new);
    }


}
