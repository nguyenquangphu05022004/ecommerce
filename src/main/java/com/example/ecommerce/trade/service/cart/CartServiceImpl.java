package com.example.ecommerce.trade.service.cart;

import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.trade.controller.cart.vo.CartCreateReqVO;
import com.example.ecommerce.trade.controller.cart.vo.CartListRespVO;
import com.example.ecommerce.trade.controller.cart.vo.CartUpdateQuantityReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.trade.enums.ErrorConstants.CART_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    @Override
    public void createCartProduct(Long userId, CartCreateReqVO reqVO) {
        Optional<Cart> opCart = this.cartRepository.findByUserMemberIdAndProductSkuId(userId, reqVO.getProductSkuId());
        Cart newCart = null;
        if(opCart.isPresent()) {
            newCart = opCart.get().toBuilder()
                    .quantity(opCart.get().getQuantity() + reqVO.getQuantity())
                    .build();
        } else {
            newCart = Cart.builder().quantity(reqVO.getQuantity())
                    .productSku(ProductSku.builder().id(reqVO.getProductSkuId()).build())
                    .selected(false).userMember(UserMember.builder().id(userId).build())
                    .build();
        }
        this.cartRepository.save(newCart);
    }

    @Override
    public CartListRespVO getList(Long userId) {
        List<Cart> carts = this.cartRepository.findAllByUserMemberId(userId);
        return new CartListRespVO(carts);
    }

    @Override
    public Cart updateQuantity(CartUpdateQuantityReqVO reqVO) {
        Cart cart = this.cartRepository.findById(reqVO.getCartId()).orElseThrow(() -> exception(CART_NOT_FOUND));
        if(reqVO.getOperand() == '+') {
            cart.setQuantity(cart.getQuantity() + 1);
        } else {
            if(cart.getQuantity() == 1) return cart;
            else {
                cart.setQuantity(cart.getQuantity() - 1);
            }
        }
        this.cartRepository.save(cart);
        return cart;
    }

    @Override
    public void delete(Long id) {
        this.cartRepository.deleteById(id);
    }
}
