package com.example.ecommerce.service;

import java.util.List;

public interface IGenericService<Dto> {
    List<Dto> records();
    void delete(Long id);
    Long count();
    Dto findById(Long id);
}
