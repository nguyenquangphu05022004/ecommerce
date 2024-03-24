package com.example.ecommerce.dao;

import org.springframework.stereotype.Repository;

public interface EventDAO <T>{
    void createEvent(T t);
}
