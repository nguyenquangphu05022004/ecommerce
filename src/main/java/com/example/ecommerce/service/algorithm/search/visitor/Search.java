package com.example.ecommerce.service.algorithm.search.visitor;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.service.request.KeySearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.security.Key;
import java.util.List;
import java.util.Map;

public class Search implements FilterVisitor{
    private Predicate predicate;
    private Root<Product> root;
    private CriteriaBuilder criteriaBuilder;
    private Map<KeySearchRequest, List<String>> data;

    public Search(Root<Product> root,
                  CriteriaBuilder criteriaBuilder,
                  Map<KeySearchRequest, List<String>> data) {
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
        this.data = data;
    }

    @Override
    public void visit(FilterProductBrand filterProductBrand) {
        if(data.containsKey(KeySearchRequest.BRAND_ID)) {
            
        }
    }

    @Override
    public void visit(FilterProductCategoryParent filterProductCategoryParent) {
        if(data.containsKey(KeySearchRequest.CATEGORY_PARENT_ID)) {

        }
    }

    @Override
    public void visit(FilterProductName filterProductName) {
        if(data.containsKey(KeySearchRequest.PRODUCT_NAME)) {

        }
    }
}
