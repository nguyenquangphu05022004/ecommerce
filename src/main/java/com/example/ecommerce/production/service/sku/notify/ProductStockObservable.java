package com.example.ecommerce.production.service.sku.notify;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductStockObservable {
    private List<ProductStockObserver> productStockObservers;
    private String data;
    public ProductStockObservable() {
        this.productStockObservers = new ArrayList<>();
    }

    public void addObserver(ProductStockObserver observer) {
        this.productStockObservers.add(observer);
    }

    public void notifyObservers() {
        for(ProductStockObserver ob : productStockObservers) {
            ob.onStockUpdate();
        }
    }

    public void setData(String jsonData) {
        this.data = jsonData;
        notifyObservers();
    }

    public String getData() {
        return data;
    }
}
