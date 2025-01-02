package com.example.ecommerce.product.controller.admin.comment.evaluation;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.product.controller.admin.comment.evaluation.vo.ProductEvaluationReqVO;
import com.example.ecommerce.product.controller.admin.comment.evaluation.vo.ProductEvaluationResVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;
import com.example.ecommerce.product.service.comment.ProductEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/product/comment/evaluations")
@Tag(name = "Product Evaluation", description = "Tieu chi danh gia, di kem voi binh luan")
public class ProductEvaluationController {
    private final ProductEvaluationService productEvaluationService;

    @PostMapping
    @Operation(summary = "Tao evaluation")
    @PreAuthorize("@ss.hasPermission('product-comment-evaluation:update')")
    private CommonResult<ProductEvaluationResVO> createEvaluation(@RequestBody ProductEvaluationReqVO req) {
        ProductEvaluation evaluation = productEvaluationService.createProductEvaluation(req);
        return success(evaluation, ProductEvaluationResVO::new);
    }

    @PutMapping
    @Operation(summary = "Cap nhat evaluation")
    @PreAuthorize("@ss.hasPermission('product-comment-evaluation:update')")
    public CommonResult<ProductEvaluationResVO> updateEvaluation(@RequestBody ProductEvaluationReqVO req) {
        return success(productEvaluationService.updateEvaluation(req), ProductEvaluationResVO::new);
    }


    @GetMapping("/spu/{spuId}")
    @Operation(summary = "Lay danh sach boi spu")
    public CommonResult<List<ProductEvaluationResVO>> getListEvaluationBySpuId(@PathVariable("spuId") Long spuId) {
        return success(convertList(productEvaluationService.getAllProductEvaluationByProductSpuId(spuId), ProductEvaluationResVO::new));
    }

}
