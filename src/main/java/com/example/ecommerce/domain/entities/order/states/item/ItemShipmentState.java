package com.example.ecommerce.domain.entities.order.states.item;

import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.State;

public class ItemShipmentState implements State<LineItem, LineItemMessage> {

    @Override
    public LineItemMessage next(LineItem lineItem) {
        return null;
    }

    @Override
    public LineItemMessage prev(LineItem lineItem) {
        return null;
    }

    @Override
    public LineItemMessage status() {
        return null;
    }
}
