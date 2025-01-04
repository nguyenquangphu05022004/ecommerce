package com.example.ecommerce.product.controller.app.comment;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.product.controller.admin.comment.vo.ProductCommentResVO;
import com.example.ecommerce.product.controller.app.comment.vo.AppProductCommentRespVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.service.comment.ProductCommentService;
import com.example.ecommerce.statistic.enums.OperationType;
import com.example.ecommerce.statistic.service.ProductStatisticService;
import com.example.ecommerce.trade.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.UPDATE_COMMENT_IS_DENIED;

@RestController
@Tag(name = "APP_COMMENT")
@RequiredArgsConstructor
@RequestMapping("/app-api/product/comments")
public class AppProductCommentController {
    private final ProductCommentService productCommentService;
    private final OrderService orderService;
    private final ProductStatisticService statisticService;
    @Operation(summary = "Lay danh sach comment cua user hien tai")
    @GetMapping("/my/page")
    public CommonResult<PageResult<AppProductCommentRespVO>> getMyPageProductComment(@RequestBody PageParam req) {
        PageResult<ProductComment> pageResult = productCommentService
                .getPageProductCommentByUserMemberId(SecurityUtils.getLoginUserMemberId(), req);
        return responsePage(pageResult);
    }

    private CommonResult<PageResult<AppProductCommentRespVO>> responsePage(PageResult<ProductComment> pageResult) {
        return CommonResult.success(pageResult, comment -> {
            return new AppProductCommentRespVO(comment, productCommentService.userHasLikeComment(
                    SecurityUtils.getLoginUserMemberId(), comment.getId()
            ));
        }, "");
    }

    @GetMapping("/spu/{spuId}/page")
    @Operation(summary = "Lay danh sach comment cua product")
    public CommonResult<PageResult<AppProductCommentRespVO>> getPageProductCommentBySpuId(@PathVariable("spuId") Long spuId,
                                                                                      @RequestBody PageParam req) {
        PageResult<ProductComment> page = productCommentService.getPageCommentByProductSpuId(spuId, req);
        return responsePage(page);
    }
    @PostMapping
    @Operation(summary = "Tao comment")
    public CommonResult<AppProductCommentRespVO> createProductComment(@RequestBody ProductCommentCreateReqVO req) {
        ProductComment productComment = productCommentService.createProductComment(req);
        statisticService.doUpdateProductStatistic(req.getProductSpuId(), OperationType.ADD, "numComment");
        return CommonResult.success(productComment,AppProductCommentRespVO::new);
    }

    @PutMapping
    @Operation(summary = "Cap nhat comment")
    public CommonResult<ProductCommentResVO> updateProductComment(@RequestBody ProductCommentCreateReqVO req) {
        req.setUserMemberId(SecurityUtils.getLoginUserMemberId());
        ProductComment productComment = productCommentService.updateProductComment(req);
        return CommonResult.success(productComment, ProductCommentResVO::new);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xoa comment, chi duoc xoa nhung comment cua user hien tai")
    public CommonResult<Boolean> deleteProductComment(@PathVariable("id") Long id) {
        ProductComment proComment = productCommentService.getProductCommentById(id);
        if(proComment.getUserMember().getId().equals(SecurityUtils.getLoginUserMemberId())) {
            this.productCommentService.delete(id);
            this.statisticService.doUpdateProductStatistic(proComment.getProductSku().getId(), OperationType.DELETE, "numComment");
            return CommonResult.success(true);
        }
        throw exception(UPDATE_COMMENT_IS_DENIED);
    }


    @PostMapping("/favorite")
    @Operation(summary = "Cap nhat luot thich comment")
    public CommonResult<Integer> updateProductFavorite(@RequestParam("commentId") Long commentId) {
        int numLike = this.productCommentService.updateCommentLike(SecurityUtils.getLoginUserMemberId(), commentId);
        return CommonResult.success(numLike);
    }

}
