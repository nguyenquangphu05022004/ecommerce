//package com.example.ecommerce.production.service.spu.notify;
//
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ProductStockObservable {
//    private List<ProductObserver> productObservers;
//    private String data;
//    public ProductStockObservable() {
//        this.productObservers = new ArrayList<>();
//    }
//
//    public void addObserver(ProductObserver observer) {
//        this.productObservers.add(observer);
//    }
//
//    public void notifyObservers() {
//        for(ProductObserver ob : productObservers) {
//            ob.onStockUpdate();
//        }
//    }
//
//    public void setData(String jsonData) {
//        this.data = jsonData;
//        notifyObservers();
//    }
//
//    public String getData() {
//        return data;
//    }
//}
