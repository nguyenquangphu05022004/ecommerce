package com.example.ecommerce.repository.dao;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Language;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Language_;
import com.example.ecommerce.domain.Product_;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class SearchProductDaoTest {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    SearchProductDaoTest(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Test
    void findAllByName() {
        Category category = Category.builder()
                .name("dsad")
                .slug("dasf")
                .build();
        Category saved = categoryRepository.save(category);
        Product product = Product.builder()
                .language(new Language("hai nam", "hai nam"))
                .category(saved)
                .build();
        productRepository.save(product);
        String name = "nam";
        List<Product> products = productRepository.findAll(nameLike(name));
        assertThat(products.size()).isGreaterThan(0);
    }

    private Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            Predicate likeWithNameEn = criteriaBuilder.like(root.join(Product_.language).get(Language_.nameEn), "%" + name.toLowerCase() + "%s");
            Predicate likeWithNameVn = criteriaBuilder.like(root.join(Product_.language).get(Language_.nameVn), "%" + name.toLowerCase() + "%s");
            return criteriaBuilder.and(likeWithNameEn, likeWithNameVn);
        };
    }
}