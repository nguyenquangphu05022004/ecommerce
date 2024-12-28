package com.example.ecommerce.product.controller.app.favorite.seller;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.product.service.favorite.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/product/favorite/sellers")
@Tag(name = "App_Favorite_Seller")
public class AppSellerFavoriteController {

    private final FavoriteService favoriteService;

    public AppSellerFavoriteController(@Qualifier("seller") FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    @Operation(summary = "Theo doi nguoi ban hang")
    public CommonResult<Boolean> followSeller(@RequestParam("sellerId") Long sellerId) {
        favoriteService.createFavorite(SecurityUtils.getLoginUserMemberId(), sellerId);
        return CommonResult.success(true);
    }
    @DeleteMapping
    @Operation(summary = "Xoa theo doi nguoi ban hang")
    public CommonResult<Boolean> unfollowSeller(@RequestParam("sellerId") Long sellerId) {
        favoriteService.deleteFavorite(SecurityUtils.getLoginUserMemberId(), sellerId);
        return CommonResult.success(true);
    }

    @GetMapping
    @Operation(summary = "Kiem tra xem user hien tai co theo doi seller cu the hay khong")
    public CommonResult<Boolean> userHasFollowSeller(@RequestParam("sellerId") Long sellerId) {
        return CommonResult.success(favoriteService.userHasFavorite(SecurityUtils.getLoginUserMemberId(), sellerId));
    }
}
