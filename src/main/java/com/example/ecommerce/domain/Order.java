package com.example.ecommerce.domain;

import com.example.ecommerce.repository.NotificationRepository;
import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL)
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
}
