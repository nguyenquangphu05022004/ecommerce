package com.example.ecommerce.production.dal.dataobject.notification;

import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;

/**
 * Notification to users who like product spu
 * spu(sku1, sku2)
 */
public class ProductSkuUpdateStockNotification {
    private UserMember userMember;
    private ProductSku productSku;
    private String content;
}
