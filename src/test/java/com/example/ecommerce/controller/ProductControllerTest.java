package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class ProductControllerTest {

    private final IProductService productService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @Test
    void test_create_product() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setBrandId(1l);
        request.setCategoryId(2l);
        request.setDescription("hello world");
        request.setPrice(2500);
        request.setCombination(false);
        request.setLanguage(new Product.Language("Ao the thao liverpool", "ao the thao liverpool"));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/products"
                ).content("application/json")
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusValue")
                        .value(400));

    }

    @Test
    void test_get_product_by_id() {
        Long productId = 1l;

    }

    @Test
    void getAllProductRecommendation() {
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void getInventoryAttributeKey() {
    }

//    private AuthenResponse login() throws Exception {
//        AuthenRequest login = new AuthenRequest();
//        login.setUsername("quangphu2050@gmail.com");
//        login.setPassword("mahiru");
//        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
//                        "/api/v1/auth/login"
//                ).contentType("application/json")
//                .content(objectMapper.writeValueAsString(login));
//        String requestResponse = mockMvc.perform(b1).andReturn().getResponse().getContentAsString();
//        AuthenResponse authenResponse = objectMapper.readValue(requestResponse, AuthenResponse.class);
//        return authenResponse;
//    }
}
