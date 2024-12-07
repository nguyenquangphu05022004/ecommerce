package com.example.ecommerce.notification;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;
import com.example.ecommerce.product.dal.dataobject.favorite.SellerFavorite;
import com.example.ecommerce.product.dal.dataobject.notification.ProductSkuUpdateStockNotification;
import com.example.ecommerce.product.dal.dataobject.notification.ProductSpuNewNotification;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.favorite.ProductFavoriteRepository;
import com.example.ecommerce.product.dal.repository.favorite.SellerFavoriteRepository;
import com.example.ecommerce.product.dal.repository.notification.ProductSkuUpdateStockNotificationRepository;
import com.example.ecommerce.product.dal.repository.notification.ProductSpuNewNotificationRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("appNotification")
@RequiredArgsConstructor
public class AppNotificationService implements NotificationObserver{
    private final ProductFavoriteRepository productFavoriteRepository;
    private final UserMemberNotificationRepository userMemberNotificationRepository;
    private final ProductSkuUpdateStockNotificationRepository productSkuUpdateStockNotificationRepository;
    private final ProductSpuRepository productSpuRepository;
    private final SellerFavoriteRepository sellerFavoriteRepository;
    private final ProductSpuNewNotificationRepository productSpuNewNotificationRepository;
    @Override
    public void onStockUpdate(String productSkuIdStr) {
        Long productSkuId = Long.parseLong(productSkuIdStr);
        List<ProductFavorite> productFavorites = productFavoriteRepository.findAllByProductSpuId(productSkuId);
        List<UserMember> userMembers = CollUtils.convertList(
                productFavorites,
                f -> f.getUserMember()
        );
        ProductSkuUpdateStockNotification productSkuUpdateStockNotification =
                ProductSkuUpdateStockNotification.builder()
                        .productSku(ProductSku.builder().id(productSkuId).build())
                        .build();
        userMembers.forEach(user -> {
            UserMemberNotification userMemberNotification = userMemberNotificationRepository.findByUserMemberId(user.getId()).get();
            if(userMemberNotification.getEnableAppNotification()) {
                productSkuUpdateStockNotification.getUserMemberNotifications().add(userMemberNotification);
            }
        });
        this.productSkuUpdateStockNotificationRepository.save(productSkuUpdateStockNotification);
    }

    @Override
    public void onCreatedNewOrder() {

    }

    @Override
    public void onCreateNewProduct(String productSpuIdStr) {
        Long productSpuId = Long.parseLong(productSpuIdStr);
        ProductSpu spu = productSpuRepository.findById(productSpuId).get();
        List<SellerFavorite> sellerFavorites = this.sellerFavoriteRepository.findAllBySellerId(spu.getSeller().getId());

        ProductSpuNewNotification productSpuNewNotification = ProductSpuNewNotification.builder()
                .productSpu(spu).build();
        for(SellerFavorite sellerFavorite : sellerFavorites) {
            UserMemberNotification userMemberNotification = userMemberNotificationRepository.findByUserMemberId(sellerFavorite.getUserMember().getId()).get();
            if(userMemberNotification.getEnableAppNotification()) {
                productSpuNewNotification.getUserMemberNotifications().add(userMemberNotification);
            }
        }
        this.productSpuNewNotificationRepository.save(productSpuNewNotification);
    }

    @Override
    public void onOrderDelivery() {

    }

}
