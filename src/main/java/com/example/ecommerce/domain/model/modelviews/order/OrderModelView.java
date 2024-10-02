package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.LineItem;
import com.example.ecommerce.domain.entities.Order;
import com.example.ecommerce.domain.entities.Payment;
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
        this.lineItems = mapToOrderViewModel(o.getLineItems());
    }

    private Set<LineItemModelView> mapToOrderViewModel(Set<LineItem> lineItems) {
        if(CollectionUtils.isEmpty(lineItems)) return Collections.emptySet();
        return lineItems.stream()
                .map(lineItem -> new LineItemModelView(lineItem))
                .collect(Collectors.toSet());
    }
}
