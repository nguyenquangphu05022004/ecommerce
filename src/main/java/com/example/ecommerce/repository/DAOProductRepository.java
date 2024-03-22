package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DAOProductRepository{
    List<Product> findAllByLanguageNameEnOrNameVnAAndCategory(String query,
                                                              Long categoryId,
                                                              Pageable pageable,
                                                              Sort sort);
}
