package com.example.ecommerce.notification;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.notification.ProductSkuUpdateStockNotification;
import com.example.ecommerce.product.dal.dataobject.notification.ProductSpuNewNotification;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "production_user_member_notification")
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class UserMemberNotification extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id", unique = true)
    private UserMember userMember;
    private Boolean enableEmailNotification;
    private Boolean enableAppNotification;

    @ManyToMany(mappedBy = "userMemberNotifications")
    private List<ProductSkuUpdateStockNotification> productSkuUpdateStockNotifications;
    @ManyToMany(mappedBy = "userMemberNotifications")
    private List<ProductSpuNewNotification> productSpuNewNotifications;
}
