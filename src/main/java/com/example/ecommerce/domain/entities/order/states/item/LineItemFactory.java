package com.example.ecommerce.domain.entities.order.states.item;

import com.example.ecommerce.domain.FactoryBuilder;
import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.State;

public class LineItemFactory extends FactoryBuilder<State<LineItem, LineItemMessage>> {
    private static LineItemFactory lineItemFactory;
    private LineItemFactory(Class clazz) {
        super(clazz);
    }
    private static LineItemFactory getInstance() {
        if(lineItemFactory == null) {
            lineItemFactory = new LineItemFactory(LineItemFactory.class);
        }
        return lineItemFactory;
    }
    public static State<LineItem, LineItemMessage> getState(String typeName) {
        return lineItemFactory.getInstance().getInstance(typeName);
    }
}
