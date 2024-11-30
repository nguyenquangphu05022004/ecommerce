package com.example.ecommerce.production.controller.comment;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.production.controller.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.production.controller.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.production.controller.comment.vo.ProductCommentResVO;
import com.example.ecommerce.production.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.production.service.comment.ProductCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-comments")
public class ProductCommentController {
    private final ProductCommentService productCommentService;

    @PostMapping
    @Operation(summary = "Comment about specific product spu")
    public CommonResult<ProductCommentResVO> createProductComment(@RequestBody ProductCommentCreateReqVO reqVO,
                                                                  @RequestParam("files")List<MultipartFile> files) {
        return CommonResult.success(productCommentService.createProductComment(reqVO, files), ProductCommentResVO::new);
    }

    @GetMapping("/product-spu")
    @Operation(summary = "Get list comment of specific product spu")
    public CommonResult<PageResult<ProductCommentResVO>> getListProductCommentByProductSpu(@RequestBody PagingProductCommentReqVO reqVO) {
        PageResult<ProductComment> pageResult = this.productCommentService.getListProductCommentByProductSpu(reqVO);
        return CommonResult.success(pageResult, ProductCommentResVO::new);
    }


}
