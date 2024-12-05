//package com.example.ecommerce.production.service.spu.notify;
//
//import com.example.ecommerce.frame.common.json.JsonUtils;
//import com.example.ecommerce.notification.mq.message.email.EmailMessage;
//import com.example.ecommerce.production.controller.sku.vo.ProductSkuUpdateStockReqVO;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EmailNotificationListener implements ProductObserver {
//
//    private final ProductStockObservable observable;
//    private final ApplicationContext applicationContext;
//    @Override
//    public void onStockUpdate() {
//        ProductSkuUpdateStockReqVO reqVO = JsonUtils.parseObject(observable.getData(), new TypeReference<ProductSkuUpdateStockReqVO>() {});
//        EmailMessage emailMessage = new EmailMessage();
//        applicationContext.publishEvent(emailMessage);
//    }
//}
