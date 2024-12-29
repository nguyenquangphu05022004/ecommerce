package com.example.ecommerce.product.controller.app.favorite.product;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.product.service.favorite.FavoriteService;
import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import com.example.ecommerce.statistic.enums.OperationType;
import com.example.ecommerce.statistic.service.ProductStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/product/favorite/products")
@Tag(name = "App_Favorite_Product")
public class AppProductFavoriteController {
    private final FavoriteService favoriteService;
    private final ProductStatisticService productStatisticService;
    public AppProductFavoriteController(@Qualifier("product") FavoriteService favoriteService, ProductStatisticService productStatisticService) {
        this.favoriteService = favoriteService;
        this.productStatisticService = productStatisticService;
    }

    @PostMapping
    @Operation(summary = "Thich product spu")
    public CommonResult<Boolean> likeProduct(@RequestParam("productSpuId") Long spuId) {
        favoriteService.createFavorite(SecurityUtils.getLoginUserMemberId(), spuId);
        productStatisticService.doUpdateProductStatistic(spuId, OperationType.ADD, "numFavorite");
        return CommonResult.success(true);
    }
    @DeleteMapping
    @Operation(summary = "Bo thich product spu")
    public CommonResult<Boolean> unfollowSeller(@RequestParam("productSpuId") Long spuId) {
        favoriteService.deleteFavorite(SecurityUtils.getLoginUserMemberId(), spuId);
        productStatisticService.doUpdateProductStatistic(spuId, OperationType.DELETE, "numFavorite");
        return CommonResult.success(true);
    }

    @GetMapping
    @Operation(summary = "user co thich product spu")
    public CommonResult<Boolean> userHasFollowSeller(@RequestParam("productSpuId") Long spuId) {
        return CommonResult.success(favoriteService.userHasFavorite(SecurityUtils.getLoginUserMemberId(), spuId));
    }
}
