package com.example.ecommerce.product.dal.dataobject.notification;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.notification.UserMemberNotification;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Shop A ban san pham moi P.
 * Notification cho tat ca users thich shop A ve san pham P.
 */
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "production_product_spu_new_notification")
@NoArgsConstructor
@Getter
@Setter
public class ProductSpuNewNotification  extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;
    private String content;

    @ManyToMany
    @JoinTable(name = "production_product_spu_new_notification_usermember",
            joinColumns = @JoinColumn(name = "product_spu_new_notification_id"),
            inverseJoinColumns = @JoinColumn(name = "user_member_notification_id"))
    private List<UserMemberNotification> userMemberNotifications;
}
