//package com.example.ecommerce.production.service.sku.notify;
//
//import com.example.ecommerce.frame.common.json.JsonUtils;
//import com.example.ecommerce.production.dal.dataobject.favorite.ProductFavorite;
//import com.example.ecommerce.production.dal.dataobject.notification.ProductSkuUpdateStockNotification;
//import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
//import com.example.ecommerce.production.dal.repository.favorite.ProductFavoriteRepository;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class AppNotificationListener implements ProductStockObserver{
//
//    private final ProductStockObservable observable;
//    private final ProductFavoriteRepository productFavoriteRepository;
//    @Override
//    public void onStockUpdate() {
//        ProductSku productSku = JsonUtils.parseObject(this.observable.getData(), new TypeReference<ProductSku>() {});
//        List<ProductFavorite> productFavorites = this.productFavoriteRepository.findAllByProductSpuId(productSku.getProductSpu().getId());
//        for(ProductFavorite pf : productFavorites) {
//            new Thread(() -> {
//                ProductSkuUpdateStockNotification proStockUpdateNotification =
//                        ProductSkuUpdateStockNotification.builder().productSku(productSku)
//                        .userMember(pf.getUserMember()).build();
//                //save message;
//                //using websocket send to user
//            }).start();
//        }
//    }
//}
