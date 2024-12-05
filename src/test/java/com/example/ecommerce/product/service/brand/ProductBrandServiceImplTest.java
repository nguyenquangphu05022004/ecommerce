package com.example.ecommerce.product.service.brand;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ConfigurationPropertiesScan("application-test.properties")
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductBrandServiceImplTest {

    @Test
    void createProductBrand() {
    }
}
