//package com.example.ecommerce.production.service.spu.notify;
//
//import com.example.ecommerce.frame.common.json.JsonUtils;
//import com.example.ecommerce.production.dal.dataobject.favorite.SellerFavorite;
//import com.example.ecommerce.production.dal.dataobject.notification.ProductSpuNewNotification;
//import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
//import com.example.ecommerce.production.dal.repository.favorite.SellerFavoriteRepository;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class AppNotificationListener implements ProductObserver {
//
//    private final ProductStockObservable observable;
//    private final SellerFavoriteRepository sellerFavoriteRepository;
//    @Override
//    @Async
//    public void onStockUpdate() {
//        ProductSpu productSpu = JsonUtils.parseObject(this.observable.getData(), new TypeReference<ProductSpu>() {});
//        List<SellerFavorite> sellerFavorites = this.sellerFavoriteRepository.findAllBySellerId(productSpu.getSeller().getId());
//        for(SellerFavorite sl : sellerFavorites) {
//                ProductSpuNewNotification productSpuNewNotification =
//                        ProductSpuNewNotification.builder().productSpu(productSpu)
//                        .content("")
//                        .userMember(sl.getUserMember()).build();
//                //save message;
//                //using websocket send to user
//        }
//    }
//}
