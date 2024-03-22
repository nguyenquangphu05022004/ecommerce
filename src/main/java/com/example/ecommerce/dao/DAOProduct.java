package com.example.ecommerce.dao;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.DAOProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class DAOProduct implements DAOProductRepository {
    @Override
    public List<Product> findAllByLanguageNameEnOrNameVnAAndCategory(String query,
                                                                     Long categoryId,
                                                                     Pageable pageable,
                                                                     Sort sort) {
        return null;
    }
}
