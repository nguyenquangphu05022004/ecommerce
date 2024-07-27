package com.example.ecommerce.domain;

import com.example.ecommerce.repository.NotificationRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Order extends BaseEntity implements Observer{

    @Embedded
    private UserContactDetails userContactDetails;

    @OneToMany(mappedBy = "order")
    private Set<LineItem> lineItems;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private boolean approval;
    private boolean purchased;
    private boolean received;

    @Override
    public void notify(NotificationRepository notificationRepository) {
        Notification notification = Notification.builder()
                .entityId(this.getId())
                .type(EntityType.ORDER)
                .message(String.format("You created a order with id %s, during 8 hours if you want to remove a order, you can do it after that you can't.", this.getId()))
                .build();
        notificationRepository.save(notification);
    }

    @AllArgsConstructor
    public enum Payment {
        PAY_AT_HOME("Thanh toán tại nhà"),
        PAY_BY_BANK("Thanh toán qua ngân hàng");
        @Getter
        private final String name;
    }


    @Getter
    @Entity
    @Table(name = "line_items")
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder(toBuilder = true)
    @Setter
    public static class LineItem extends BaseEntity{
        @ManyToOne
        @JoinColumn(name = "vendor_id", nullable = false)
        private Vendor vendor;
        @OneToMany(mappedBy = "lineItem")
        private Set<Item> items;
        @ManyToOne
        @JoinColumn(name = "coupon_id")
        private Coupon coupon;
        @ManyToOne
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;
    }
    @Entity
    @Table(name = "items")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @SuperBuilder(toBuilder = true)
    @Setter
    public static class Item extends BaseEntity{
        @ManyToOne
        @JoinColumn(name = "stock_id")
        private Stock stock;
        @ManyToOne
        @JoinColumn(name = "stock_classificationId")
        private Stock.StockClassification stockClassification;
        @Column(nullable = false)
        private Integer quantity;
        @ManyToOne
        @JoinColumn(name = "line_item_id")
        private LineItem lineItem;
    }

}
