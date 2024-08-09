package com.example.ecommerce.service.algorithm.search.visitor;

public interface FilterVisitor {
    void visit(FilterProductBrand filterProductBrand);
    void visit(FilterProductCategoryParent filterProductCategoryParent);
    void visit(FilterProductName filterProductName);
}
