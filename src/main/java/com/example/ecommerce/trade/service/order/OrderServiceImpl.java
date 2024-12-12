package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.promotion.controller.coupon.CouponRespVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.service.coupon.CouponService;
import com.example.ecommerce.system.controller.user.vo.SellerResVO;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.AddressRepository;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.trade.controller.cart.vo.CartItemRespVO;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckout;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutReqVO;
import com.example.ecommerce.trade.controller.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderItem;
import com.example.ecommerce.trade.dal.dataobject.order.OrderStatus;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderItemRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMap;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.USER_ADDRESS_NOT_FOUND;
import static com.example.ecommerce.trade.enums.ErrorConstants.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final ProductSkuRepository productSkuRepository;
    private final SellerRepository sellerRepository;

    private final CouponService couponService;
    @Override
    @Transactional
    public void createOrder(Long userId, OrderDetailsReqVO reqVO) {

        /**
         * Phieu giam gia
         */
        Set<Coupon> coupon = CollUtils.convertSet(reqVO.getOrderCheckout().getItemCheckout(), (seller, pair) -> {
            return Set.of(Coupon.builder().id(pair.getValue().getId()).build());
        });

        /**
         * Item order request
         */
        Set<CartItemRespVO> cartItems = CollUtils.convertSet(reqVO.getOrderCheckout().getItemCheckout(), (seller, pair) -> {
            return pair.getKey();
        });


        /**
         * Tao order
         */
        Order order = Order.builder().orderStatus(OrderStatus.PENDING)
                .no(System.currentTimeMillis() + "").commentStatus(false)
                .paymentMode(reqVO.getPaymentMode()).coupons(coupon)
                .address(this.addressRepository.findById(reqVO.getAddressId()).orElseThrow(() -> exception(USER_ADDRESS_NOT_FOUND)).detailAddress())
                .userMember(UserMember.builder().id(userId).build()).build();


        /**
         * update stock cua product
         */
        Set<ProductSku> productSkus = convertSet(cartItems, cart -> {
            ProductSku productSku = this.productSkuRepository.findById(cart.getProduct().getId()).get();
            productSku.setQuantity(productSku.getQuantity() - cart.getQuantity());
            return productSku;
        });

        /**
         * Tao orderItem de luu trong database
         */
        Set<OrderItem> orderOrderItems = convertSet(cartItems, cart -> {
            return OrderItem.builder().quantity(cart.getQuantity())
                    .productSku(ProductSku.builder().id(cart.getProduct().getId()).build())
                    .order(order)
                    .build();
        });

        /**
         * Luu vao db
         */
        this.orderRepository.save(order);
        this.orderItemRepository.saveAll(orderOrderItems);
        this.productSkuRepository.saveAll(productSkus);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> exception(ORDER_NOT_FOUND));
        if(order.getOrderStatus() == OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.CANCEL);
            this.orderRepository.save(order);
        } else {
            throw exception(ORDER_APPROVAL);
        }
    }

    @Override
    public OrderCheckout checkout(Long userId, OrderCheckoutReqVO reqVO) {
        /**
         * Apply coupon code cho customer, khi nguoi dung nhap ma giam gia cua shop
         */
        Set<Coupon> coupons = convertSet(reqVO.getCouponCodes(), code -> this.couponService.getValidCoupon(code));
        /**
         * Lay thong tin ve item va so luong cua no trong gio hang
         */
        Set<Cart> carts = convertSet(reqVO.getCartIds(), cartId -> {
            return this.cartRepository.findById(cartId).orElseThrow(() -> exception(CART_NOT_FOUND));
        });

        Map<SellerResVO, CouponRespVO> sellerMapCoupon = convertToMap(convertSet(coupons, s -> {
            return new Pair<>(new SellerResVO(s.getOwner()), new CouponRespVO(s));
        }));

        Map<SellerResVO, Set<CartItemRespVO>> sellerMapSetCart = convertToMapSet(convertSet(carts, cart -> {
            return new Pair<>(new SellerResVO(cart.getProductSku().getProductSpu().getSeller()), new CartItemRespVO(cart));
        }));
        /**
         * Combination two map
         */
        Map<SellerResVO, Pair<Set<CartItemRespVO>, CouponRespVO>> itemCheckout = MapUtils.combinationTwoMap(
                sellerMapSetCart, sellerMapCoupon, (cartItems, coupon) -> new Pair<>(cartItems, coupon)
        );

        return OrderCheckout.builder()
                .itemCheckout(itemCheckout)
                .build();

    }

    @Override
    public List<Order> getAllListOrder(Long userId) {
        return this.orderRepository.findAllByUserMemberId(userId);
    }

    @Override
    public List<Order> getAllListOrderByStatus(Long userId, OrderStatus orderStatus) {
        return this.orderRepository.findAllByUserMemberIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public void updateNextStatus(Long orderId) {

    }

    @Override
    public void updatePreviousStatus(Long orderId) {

    }


}
