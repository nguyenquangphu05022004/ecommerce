package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.entities.order.Payment;
import com.example.ecommerce.domain.model.modelviews.order.LineItemModelView;

import java.util.Set;

public class OrderRequest {
    private Payment payment;
    private OrderStatus orderStatus;
    private Set<LineItemModelView> lineItems;
}
