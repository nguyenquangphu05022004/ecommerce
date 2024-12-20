package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.dal.repo.coupon.CouponRepository;
import com.example.ecommerce.system.dal.dataobject.user.Address;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.AddressRepository;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderStatus;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest extends TestBase {
    @Autowired private SellerRepository sellerRepository;
    @Autowired private ProductSpuRepository spuRepository;
    @Autowired private ProductSkuRepository skuRepository;
    @Autowired private OrderService orderService;
    @Autowired private CartRepository cartRepository;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private CouponRepository couponRepository;

    @Test
    void test_createOrder_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 100);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);
//        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, o -> {
//           o.setAddressId(ad.getId());  o.setCartIds(CollUtils.convertSet(randomCart(5, sku).stream().limit(1).collect(Collectors.toList()), s -> s.getId()));
//        });
//        this.orderService.createOrder(userMember.getId(), req);

        List<Order> allListOrder = this.orderService.getAllListOrder(userMember.getId());

        assertEquals(allListOrder.size(), 1);
    }


    @Test
    void test_checkout_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 5);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);
        Coupon coupon = randomCoupon(seller);

        Seller seller1 = randomSeller();
        ProductSpu spu1 = randomSpu(seller1);
        ProductSku sku1 = randomSku(spu1, 5);
        Coupon coupon1 = randomCoupon(seller1);

        OrderCheckoutReqVO resVO = RandomUtils.randomPojo(OrderCheckoutReqVO.class, o -> {
            o.setCouponCodes(Set.of(coupon.getCode(), coupon1.getCode()));
            o.setCartIds(Set.of(randomCart(5, sku).get(0).getId(), randomCart(5, sku1).get(0).getId()));
        });
        OrderCheckout checkout = orderService.checkout(userMember.getId(), resVO);
    }

    @Test
    void test_getAllListOrderByStatus_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 5);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);

//        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, o -> {
//            o.setAddressId(ad.getId());  o.setCartIds(CollUtils.convertSet(randomCart(1, sku).stream().limit(1).collect(Collectors.toList()), s -> s.getId()));
//        });
//        orderService.createOrder(userMember.getId(), req);
//        orderService.createOrder(userMember.getId(), req);
//        orderService.createOrder(userMember.getId(), req);

        List<Order> allListOrderByStatus = this.orderService.getAllListOrderByStatus(userMember.getId(), OrderStatus.PENDING);
        assertEquals(allListOrderByStatus.size(), 3);
    }

    @Test
    void updateNextStatus() {
    }

    @Test
    void updatePreviousStatus() {
    }
    UserMember randUserMember() {
        UserMember m = new UserMember();
        this.userMemberRepository.save(m);
        return m;
    }
    Address randAddress(UserMember u) {
        Address build = Address.builder().user(u).defaultAddress(true).build();
        this.addressRepository.save(build);
        return build;
    }
    Seller randomSeller() {
        Seller seller = new Seller();
        this.sellerRepository.save(seller);
        return seller;
    }
    ProductSpu randomSpu(Seller sell) {
        ProductSpu build = ProductSpu.builder().seller(sell).build();
        this.spuRepository.save(build);
        return build;
    }
    ProductSku randomSku(ProductSpu spu, int quantity) {
        ProductSku build = ProductSku.builder().quantity(quantity)
                .price(50000)
                .productSpu(spu).build();
        this.skuRepository.save(build);
        return build;
    }


    Coupon randomCoupon(Seller seller) {
        Coupon coupon = RandomUtils.randomPojo(Coupon.class, s -> {
            s.setId(null);
            s.setOwner(seller); s.setBeginDate(LocalDateTime.now().minusDays(2l));
            s.setEndDate(LocalDateTime.now().plusDays(5l));
        });
        this.couponRepository.save(coupon);
        return coupon;
    }

    List<Cart> randomCart(Integer quantityItem, ProductSku sku) {
        List<Cart> carts = RandomUtils.randomList(Cart.class, c -> {
            c.setQuantity(quantityItem);
            c.setId(null);
            c.setProductSku(sku);
            c.setUserMember(null);
        });
        this.cartRepository.saveAll(carts);
        return carts;
    }

}
