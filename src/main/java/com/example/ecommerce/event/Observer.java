package com.example.ecommerce.event;

public interface Observer<T>{
    void notify(T t);
}
