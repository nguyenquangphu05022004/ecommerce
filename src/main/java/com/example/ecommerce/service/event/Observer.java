package com.example.ecommerce.service.event;

public interface Observer<T>{
    void notify(T t);
}
