package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.entities.order.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class OrderViewModel extends BaseEntity {
    private Payment payment;
    private OrderStatus orderStatus;
    private Set<LineItemModelView> lineItems;

    public OrderViewModel(Order o) {
        setId(o.getId());
        setCreatedBy(o.getCreatedBy());
        this.payment = o.getPayment();
        this.orderStatus = o.getOrderStatus();
        this.lineItems = mapToOrderViewModel(o.getLineItems());
    }

    private Set<LineItemModelView> mapToOrderViewModel(Set<LineItem> lineItems) {
        if(CollectionUtils.isEmpty(lineItems)) return Collections.emptySet();
        return lineItems.stream()
                .map(lineItem -> new LineItemModelView(lineItem))
                .collect(Collectors.toSet());
    }
}
