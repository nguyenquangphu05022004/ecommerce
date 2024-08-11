package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public abstract class  ProductFilterStrategy {
    protected FilterData filterData;
    public abstract Predicate filter();
}
