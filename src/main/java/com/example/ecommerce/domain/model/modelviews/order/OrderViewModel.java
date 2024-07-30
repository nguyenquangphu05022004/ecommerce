package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.Payment;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class OrderViewModel {
    private Payment payment;
    private OrderStatus orderStatus;
    private boolean approval;
    private boolean purchased;
    private boolean received;
    private Set<LineItemModelView> lineItems;

    public OrderViewModel(Order o) {
        this.payment = o.getPayment();
        this.orderStatus = o.getOrderStatus();
        this.approval = o.isApproval();
        this.purchased = o.isPurchased();
        this.received = o.isReceived();
        this.lineItems = mapToOrderViewModel(o.getLineItems());
    }

    private Set<LineItemModelView> mapToOrderViewModel(Set<LineItem> lineItems) {
        return lineItems.stream()
                .map(lineItem -> new LineItemModelView(lineItem))
                .collect(Collectors.toSet());
    }
}
