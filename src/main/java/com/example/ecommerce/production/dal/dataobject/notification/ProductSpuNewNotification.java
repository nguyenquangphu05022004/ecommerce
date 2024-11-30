package com.example.ecommerce.production.dal.dataobject.notification;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;

/**
 * Shop A ban san pham moi P.
 * Notification cho tat ca users thich shop A ve san pham P.
 */
public class ProductSpuNewNotification extends BaseEntity {
    private UserMember userMember;
    private ProductSpu productSpu;
    private String content;
}
