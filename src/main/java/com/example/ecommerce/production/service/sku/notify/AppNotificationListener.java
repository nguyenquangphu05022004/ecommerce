package com.example.ecommerce.production.service.sku.notify;

import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.notification.mq.message.app.AppMessage;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuUpdateStockReqVO;
import com.example.ecommerce.production.dal.repository.sku.ProductSkuRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppNotificationListener implements ProductStockObserver{

    private final ProductStockObservable observable;
    private final ProductSkuRepository productSkuRepository;
    @Override
    public void onStockUpdate() {
        ProductSkuUpdateStockReqVO reqVO = JsonUtils.parseObject(observable.getData(), new TypeReference<ProductSkuUpdateStockReqVO>() {});
        AppMessage appMessage = new AppMessage();
    }
}
