package com.example.ecommerce.product.dal.dataobject.notification;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.notification.UserMemberNotification;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Notification to users who like product spu
 * spu(sku1, sku2)
 */
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "production_notification_update_product_sku_stock")
@NoArgsConstructor
@Setter
public class ProductSkuUpdateStockNotification extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;
    private String content;

    @ManyToMany
    @JoinTable(name = "production_update_stock_notification_user_member",
            joinColumns = @JoinColumn(name = "product_sku_update_stock_notification_id"),
            inverseJoinColumns = @JoinColumn(name = "user_member_notification_id"))
    private List<UserMemberNotification> userMemberNotifications;

    public List<UserMemberNotification> getUserMemberNotifications() {
        if(userMemberNotifications == null) {
            userMemberNotifications =  new ArrayList<>();
        }
        return userMemberNotifications;
    }
}
