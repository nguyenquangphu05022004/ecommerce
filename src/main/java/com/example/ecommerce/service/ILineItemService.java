package com.example.ecommerce.service;

public interface ILineItemService {
    void updateState(Long lineItemId, boolean isNext);
}
