package com.example.ecommerce.trade.dal.dataobject.order;

public class OrderLog {
    private Order order;

    private OrderStatus previousStatus;
    private OrderStatus nextStatus;

    private String content;
}
