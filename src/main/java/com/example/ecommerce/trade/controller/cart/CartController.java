package com.example.ecommerce.trade.controller.cart;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.trade.controller.cart.vo.CartCreateReqVO;
import com.example.ecommerce.trade.controller.cart.vo.CartResVO;
import com.example.ecommerce.trade.controller.cart.vo.CartUpdateQuantityReqVO;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @PostMapping
    @PermitAll
    public CommonResult<Boolean> createCart(@RequestBody CartCreateReqVO req) {
        //create cart
        return CommonResult.success(true);
    }

    @GetMapping
    @PermitAll
    public CommonResult<List<CartResVO>> getListCart() {
        List<CartResVO> cartResVOS = null;
        return CommonResult.success(cartResVOS);
    }
    @PutMapping
    @PermitAll
    public CommonResult<CartResVO> updateCart(@RequestBody CartUpdateQuantityReqVO reqVO) {
        CartResVO cartResVO = null;
        return CommonResult.success(cartResVO);
    }

    @DeleteMapping("/{cartId}")
    @PermitAll
    public CommonResult<Boolean> deleteCart(@PathVariable("cartId") Long cartId) {
        //delete
        return CommonResult.success(true);
    }
}
