package com.example.ecommerce.repository.dao;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Language_;
import com.example.ecommerce.domain.Product_;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchProductDao {

    private final ProductRepository productRepository;

    public List<Product> findAllByName(String name) {
        return productRepository.findAll(nameLike(name));
    }

    private Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> {

            Predicate likeWithNameEn = criteriaBuilder.like(root.join(Product_.language).get(Language_.nameEn), "%" + name.toLowerCase() + "%");
            Predicate likeWithNameVn = criteriaBuilder.like(root.join(Product_.language).get(Language_.nameVn), "%" + name.toLowerCase() + "%");
            return criteriaBuilder.or(likeWithNameEn, likeWithNameVn);
        };
    }

}
