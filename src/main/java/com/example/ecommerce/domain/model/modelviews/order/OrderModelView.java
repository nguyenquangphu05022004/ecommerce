package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class OrderModelView extends BaseEntity {
    private Payment payment;
    private String stateName;
    private Set<LineItemModelView> lineItems;
    private List<String> orderStateMessages;
    public OrderModelView(Order o) {
        setId(o.getId());
        setCreatedBy(o.getCreatedBy());
        this.payment = o.getPayment();
        this.stateName = o.getStateName();
        this.lineItems = mapToOrderViewModel(o.getLineItems());
        this.orderStateMessages = o.getOrderStateMessages().stream()
                .map(s -> s.getMessage())
                .collect(Collectors.toList());;
    }

    private Set<LineItemModelView> mapToOrderViewModel(Set<LineItem> lineItems) {
        if(CollectionUtils.isEmpty(lineItems)) return Collections.emptySet();
        return lineItems.stream()
                .map(lineItem -> new LineItemModelView(lineItem))
                .collect(Collectors.toSet());
    }
}
