package com.example.ecommerce.trade.controller.app.cart;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.controller.app.cart.vo.CartCreateReqVO;
import com.example.ecommerce.trade.controller.app.cart.vo.CartRespVO;
import com.example.ecommerce.trade.service.cart.CartService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/trade/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CommonResult<Boolean> createCart(@RequestBody CartCreateReqVO req) {
        //create cart
        cartService.createCartProduct(SecurityUtils.getLoginUserMemberId(), req);
        return CommonResult.success(true);
    }

    @GetMapping
    public CommonResult<List<CartRespVO>> getListCart() {
        return CommonResult.success(this.cartService.getList(SecurityUtils.getLoginUserMemberId()));
    }
//    @PutMapping
//    @PermitAll
//    public CommonResult<CartResVO> updateCart(@RequestBody CartUpdateQuantityReqVO reqVO) {
//        CartResVO cartResVO = null;
//        return CommonResult.success(cartResVO);
//    }

    @DeleteMapping("/{cartId}")
    public CommonResult<Boolean> deleteCart(@PathVariable("cartId") Long cartId) {
        //delete
        return CommonResult.success(true);
    }
}
