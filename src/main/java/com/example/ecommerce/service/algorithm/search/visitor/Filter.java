package com.example.ecommerce.service.algorithm.search.visitor;

public interface Filter {
    void accept(FilterVisitor filterVisitor);
}
