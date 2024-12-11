package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.test.AssertUtils;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.Address;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.AddressRepository;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutReqVO;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutResVO;
import com.example.ecommerce.trade.controller.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderStatus;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ecommerce.product.constants.ProductionErrorConstant.STOCK_NOT_ENOUGH;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest extends TestBase {
    @Autowired private SellerRepository sellerRepository;
    @Autowired private ProductSpuRepository spuRepository;
    @Autowired private ProductSkuRepository skuRepository;
    @Autowired private OrderService orderService;
    @Autowired private CartRepository cartRepository;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private AddressRepository addressRepository;
    @Test
    void test_createOrder_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 100);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);
        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, o -> {
           o.setAddressId(ad.getId());  o.setCartIds(CollUtils.convertSet(randomCart(5, sku).stream().limit(1).collect(Collectors.toList()), s -> s.getId()));
        });
        this.orderService.createOrder(userMember.getId(), req);

        List<Order> allListOrder = this.orderService.getAllListOrder(userMember.getId());

        assertEquals(allListOrder.size(), 1);
    }

    @Test
    void test_createOrder_stock_sku_not_enough_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 5);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);
        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, o -> {
            o.setAddressId(ad.getId());  o.setCartIds(CollUtils.convertSet(randomCart(7, sku).stream().limit(1).collect(Collectors.toList()), s -> s.getId()));
        });
        AssertUtils.assertException(STOCK_NOT_ENOUGH, () -> {
            this.orderService.createOrder(userMember.getId(), req);
        });
    }

    @Test
    void test_checkout_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 5);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);
        OrderCheckoutReqVO resVO = RandomUtils.randomPojo(OrderCheckoutReqVO.class, o -> {
            o.setCartIds(CollUtils.convertSet(randomCart(1, sku).stream().limit(1).collect(Collectors.toList()), s -> s.getId()));
        });
        OrderCheckoutResVO checkout = orderService.checkout(userMember.getId(), resVO);
        AssertUtils.assertPojoEquals(checkout.getAddress(), ad);
        assertEquals(checkout.getLineItems().size(), 1);
    }

    @Test
    void test_getAllListOrderByStatus_success() {
        Seller seller = randomSeller();
        ProductSpu spu = randomSpu(seller);
        ProductSku sku = randomSku(spu, 5);
        UserMember userMember = randUserMember();
        Address ad = randAddress(userMember);
        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, o -> {
            o.setAddressId(ad.getId());  o.setCartIds(CollUtils.convertSet(randomCart(1, sku).stream().limit(1).collect(Collectors.toList()), s -> s.getId()));
        });
        orderService.createOrder(userMember.getId(), req);
        orderService.createOrder(userMember.getId(), req);
        orderService.createOrder(userMember.getId(), req);

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
        ProductSku build = ProductSku.builder().quantity(quantity).productSpu(spu).build();
        this.skuRepository.save(build);
        return build;
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
