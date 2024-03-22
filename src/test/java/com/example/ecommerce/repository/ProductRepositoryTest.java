package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Language;
import com.example.ecommerce.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void findAllByLanguageNameEnOrNameVn() {
        //given
        String query  = "ao";
        //when
        List<Product> list = productRepository.findAllByLanguageNameEnOrNameVn(query);
        //then
        assertThat(list.size()).isEqualTo(2);
    }
}