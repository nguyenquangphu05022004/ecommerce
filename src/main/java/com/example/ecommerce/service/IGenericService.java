package com.example.ecommerce.service;

import java.util.List;

public interface IGenericService<Dto> {
    List<Dto> getAll();
    void delete(Long id);
    Dto findById(Long id);
}
