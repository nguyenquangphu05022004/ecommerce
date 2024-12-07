package com.example.ecommerce.notification;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.dal.dataobject.favorite.SellerFavorite;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.favorite.ProductFavoriteRepository;
import com.example.ecommerce.product.dal.repository.favorite.SellerFavoriteRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emailNotification")
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationObserver{
    private final EmailService emailService;
    private final ProductFavoriteRepository productFavoriteRepository;
    private final UserMemberNotificationRepository userMemberNotificationRepository;
    private final ProductSpuRepository productSpuRepository;
    private final SellerFavoriteRepository sellerFavoriteRepository;
    @Override
    public void onStockUpdate(String productSkuIdStr) {
        Long productSkuId = Long.parseLong(productSkuIdStr);
        List<UserMember> userMembers = CollUtils.convertList(
                productFavoriteRepository.findAllByProductSpuId(productSkuId),
                f -> f.getUserMember()
        );
        for(UserMember userMember : userMembers) {
//            new Thread(() -> {
                UserMemberNotification userMemberNotification = userMemberNotificationRepository.findByUserMemberId(userMember.getId()).get();
                if(userMemberNotification.getEnableEmailNotification()) {
                    emailService.sendMail("Product Updated stock", "", userMember.getEmail());
                }
//            }).start();
        }
    }

    @Override
    public void onCreatedNewOrder() {

    }

    @Override
    public void onCreateNewProduct(String productSpuIdStr) {
        Long productSpuId = Long.parseLong(productSpuIdStr);
        ProductSpu spu = productSpuRepository.findById(productSpuId).get();
        List<SellerFavorite> sellerFavorites = this.sellerFavoriteRepository.findAllBySellerId(spu.getSeller().getId());
        for(SellerFavorite sellerFavorite : sellerFavorites) {
            UserMemberNotification userMemberNotification = userMemberNotificationRepository.findByUserMemberId(sellerFavorite.getUserMember().getId()).get();
            if(userMemberNotification.getEnableEmailNotification()) {
                this.emailService.sendMail("Seller has new product", "", userMemberNotification.getUserMember().getEmail());
            }
        }
    }

    @Override
    public void onOrderDelivery() {

    }

}
