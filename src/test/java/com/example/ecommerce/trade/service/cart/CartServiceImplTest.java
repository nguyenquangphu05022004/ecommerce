package com.example.ecommerce.trade.service.cart;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.AssertUtils;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyValueRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuPropertyRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.controller.admin.user.vo.SellerResVO;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.trade.controller.app.cart.vo.CartCreateReqVO;
import com.example.ecommerce.trade.controller.app.cart.vo.CartItemRespVO;
import com.example.ecommerce.trade.controller.app.cart.vo.CartListRespVO;
import com.example.ecommerce.trade.controller.app.cart.vo.CartUpdateQuantityReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartServiceImplTest extends TestBase {

    @Autowired private ProductSpuRepository productSpuRepository;
    @Autowired private ProductSkuRepository productSkuRepository;
    @Autowired private ProductPropertyRepository productPropertyRepository;
    @Autowired private ProductPropertyValueRepository productPropertyValueRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private CartService cartService;
    @Autowired private ProductSkuPropertyRepository productSkuPropertyRepository;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private SellerRepository sellerRepository;
    @Test
    void test_createCartProduct_success() {
        Seller seller = randSeller();
        ProductSpu spu = random1(seller);
        ProductSku sku = randomSku(spu);
        ProductProperty p1 = randomProperty("size");
        ProductProperty p2 = randomProperty("color");
        List<ProductSkuProperty> productSkuProperties = randomSkuProperty(sku, p1, p2);
        for(int i = 0; i < 5; i++) {
            randomSkuProperty(randomSku(spu), p1, p2);
        }
        UserMember userMember = randUser();
        CartCreateReqVO c = RandomUtils.randomPojo(CartCreateReqVO.class, s -> {
            s.setQuantity(1); s.setProductSkuId(sku.getId());
        });
        this.cartService.createCartProduct(userMember.getId(), c);
//        this.cartService.createCartProduct(userMember.getId(), c);
        List<Cart> carts = this.cartRepository.findAllByUserMemberId(userMember.getId());

        assertEquals( carts.size(), 1);
        AssertUtils.assertPojoEquals(carts.get(0).getUserMember(), userMember);
        assertEquals(carts.get(0).getQuantity(), c.getQuantity());
//        assertEquals(carts.get(0).getQuantity(), 10);
    }



    @Test
    void test_updateQuantity_success() {
        test_createCartProduct_success();
        CartUpdateQuantityReqVO reqVO = RandomUtils.randomPojo(CartUpdateQuantityReqVO.class, s-> {
            s.setCartId(1l); s.setOperand('-');
        });
        Cart cart = this.cartService.updateQuantity(reqVO);
        Cart cart1 = this.cartRepository.findById(1l).get();
        assertEquals(cart1.getQuantity(), 1);
        AssertUtils.assertPojoEquals(cart, cart1);

    }


    public UserMember randUser() {
        UserMember userMember = RandomUtils.randomPojo(UserMember.class, s -> {
            s.setId(null);
        });
        this.userMemberRepository.save(userMember);
        return userMember;
    }
    public Seller randSeller() {
        Seller seller = new Seller();
        this.sellerRepository.save(seller);
        return seller;
    }
    public ProductSpu random1(Seller seller) {
        ProductSpu productSku = RandomUtils.randomPojo(ProductSpu.class, p -> {
            p.setProductSkus(null);p.setSeller(seller);
            p.setName("test search product");
            p.setProductBrand(null); p.setProductCategory(null);
            p.setId(null);
        });
        this.productSpuRepository.save(productSku);
        return productSku;
    }
    public ProductSku randomSku(ProductSpu spu) {
        ProductSku sku = RandomUtils.randomPojo(ProductSku.class, s -> {
            s.setProductSkuProperties(null); s.setId(null);
            s.setPrice(500_000); s.setProductSpu(spu);
        });
        this.productSkuRepository.save(sku);
        return sku;
    }
    public List<ProductSkuProperty> randomSkuProperty(ProductSku sku, ProductProperty property1, ProductProperty property2) {
        ProductSkuProperty b1 = ProductSkuProperty.builder().productSku(sku)
                .productProperty(property1).productPropertyValue(randomPropertyValue(property1))
                .build();
        ProductSkuProperty b2 = ProductSkuProperty.builder().productSku(sku)
                .productProperty(property2).productPropertyValue(randomPropertyValue(property2))
                .build();
        this.productSkuPropertyRepository.save(b1);
        this.productSkuPropertyRepository.save(b2);
        return List.of(b1, b2);
    }
    public ProductProperty randomProperty(String property) {
        return this.productPropertyRepository.save(RandomUtils.randomPojo(ProductProperty.class, s -> {
            s.setId(null); s.setName(property);
        }));
    }
    public ProductPropertyValue randomPropertyValue(ProductProperty p1) {
        return this.productPropertyValueRepository.save(RandomUtils.randomPojo(ProductPropertyValue.class, s -> {
            s.setId(null); s.setProductProperty(p1);
        }));
    }

}
