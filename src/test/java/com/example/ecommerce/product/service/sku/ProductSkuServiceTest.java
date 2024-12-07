package com.example.ecommerce.product.service.sku;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.notification.UserMemberNotification;
import com.example.ecommerce.notification.UserMemberNotificationRepository;
import com.example.ecommerce.product.controller.sku.vo.ProductSkuUpdateStockReqVO;
import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.favorite.ProductFavoriteRepository;
import com.example.ecommerce.product.dal.repository.notification.ProductSkuUpdateStockNotificationRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

class ProductSkuServiceTest extends TestBase {

    @Autowired private ProductSkuService productSkuService;
//    @Autowired private ProductSkuUpdateStockNotificationRepository productSkuUpdateStockNotificationRepository;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private UserMemberNotificationRepository userMemberNotificationRepository;
    @Autowired private ProductSkuRepository productSkuRepository;
    @Autowired private ProductSpuRepository productSpuRepository;
    @Autowired private ProductFavoriteRepository productFavoriteRepository;
    @Test
    void test_updateProductSkuStock_and_notification_through_app_and_email_success() {
        //given
        ProductSpu spu = ProductSpu.builder().build(); this.productSpuRepository.save(spu);
        ProductSku sku = ProductSku.builder().productSpu(spu).quantity(0).build(); this.productSkuRepository.save(sku);

        UserMember userMember = UserMember.builder().email("quangphu2050@gmail.com").build(); this.userMemberRepository.save(userMember);
        UserMemberNotification userMemberNotification = UserMemberNotification.builder().userMember(userMember)
                .enableAppNotification(true).enableEmailNotification(true).build();
        this.userMemberNotificationRepository.save(userMemberNotification);

        ProductFavorite productFavorite = ProductFavorite.builder().productSpu(spu).userMember(userMember).build();
        this.productFavoriteRepository.save(productFavorite);

        //when
        ProductSkuUpdateStockReqVO req = RandomUtils.randomPojo(ProductSkuUpdateStockReqVO.class, s -> {
            s.setProductSkuId(sku.getId()); s.setNewStock(50);
        });
        this.productSkuService.updateProductSkuStock(req);
        //then
        UserMemberNotification actualResult = this.userMemberNotificationRepository.findByUserMemberId(userMember.getId()).get();

        Assertions.assertEquals(1, actualResult.getProductSkuUpdateStockNotifications().size());

    }
}
