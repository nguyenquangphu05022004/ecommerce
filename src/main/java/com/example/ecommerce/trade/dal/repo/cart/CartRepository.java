package com.example.ecommerce.trade.dal.repo.cart;

import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserMemberIdAndProductSkuId(Long userMemberId, Long skuId);
    List<Cart> findAllByUserMemberId(Long userId);
}
