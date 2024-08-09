package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.CartRequest;
import com.example.ecommerce.domain.model.modelviews.cart.VendorCartModelView;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingCartControllerTest {

    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private VendorRepository vendorRepository;
    @Autowired private TokenRepository tokenRepository;
    @Autowired private MockMvc mockMvc;
    @Autowired private PasswordEncoder encoder;
    @Autowired private InventoryRepository inventoryRepository;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private RedisTemplate<String, String> redisTemplate;
    private AuthenResponse authenResponse;
    @BeforeEach
    public void init() throws Exception {
        Vendor vendor = Vendor.builder().shopName("test").perMoneyDelivery(300).build();
        vendorRepository.save(vendor);
        User user = User.builder().username("test2004").password(encoder.encode("test2004")).role(Role.VENDOR).userTypeId(vendor.getId()).userType(UserType.VENDOR).fullName("test").build();
        userRepository.save(user);
        Product product = Product.builder().productBrand(ProductBrand.builder().id(1l).build()).category(Category.builder().id(2l).build()).price(300).description("hello world").combination(false).language(new Product.Language("but bi thang long", "but bi thang long")).slug("but-bi-thang-long").vendor(vendor).build();
        productRepository.save(product);
        ProductInventory in1 = ProductInventory.builder().product(product).skuCode("pen-red").quantity(3).attributeCombinationKey("Color:Red").numberOfProductSold(0).build();
        ProductInventory in2 = ProductInventory.builder().product(product).skuCode("pen-black").quantity(2).attributeCombinationKey("Color:Black").numberOfProductSold(0).build();
        ProductInventory in3 = ProductInventory.builder().product(product).skuCode("pen-blue").quantity(4).attributeCombinationKey("Color:Blue").numberOfProductSold(0).build();
        inventoryRepository.save(in1);
        inventoryRepository.save(in2);
        inventoryRepository.save(in3);
        this.authenResponse = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType("application/json").content(objectMapper.writeValueAsString(new AuthenRequest("test2004", "test2004")))).andReturn().getResponse().getContentAsString(), AuthenResponse.class);

    }
    @AfterEach
    public void destroy() {
        tokenRepository.deleteAll();
        inventoryRepository.deleteAll();
        productRepository.deleteAll();
        vendorRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void test_add_product() throws Exception {
        Vendor vendor = vendorRepository.findAll().stream().findFirst().get();
        CartRequest cartRequest = new CartRequest();
        cartRequest.setQuantity(1);
        cartRequest.setInventoryId(vendor.getProducts().get(0).getProductInventory().get(0).getId());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/shopping-cart/products/inventories"
                ).contentType("application/json")
                .header("Authorization", getBearerToken())
                .content(objectMapper.writeValueAsString(cartRequest));
        mockMvc.perform(builder).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("A product is added to cart"));
    }

    private String getBearerToken() {
        return "Bearer " + this.authenResponse.getToken();
    }

    @Test
    void test_get_shopping_cart() throws Exception {
        test_add_product();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                "/api/v1/shopping-cart/products/inventories"
        ).header("Authorization", getBearerToken());

        String response = mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<VendorCartModelView> cart = objectMapper.readValue(response, ArrayList.class);
        assertThat(cart.size()).isGreaterThan(0);
    }

    @Test
    void delete_product_from_shopping_cart_when_not_item_in_cart() throws Exception {
        Vendor vendor = vendorRepository.findAll().stream().findFirst().get();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(
                        "/api/v1/shopping-cart/products/inventories"
                ).queryParam("inventoryId", vendor.getProducts().get(0).getProductInventory().get(1).getId().toString())
                .queryParam("vendorId", vendor.getId().toString())
                .header("Authorization", getBearerToken());
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("You haven't item in cart"));
    }

    @Test
    void delete_product_from_shopping_cart_when_item_in_cart() throws Exception {
        test_add_product();
        Vendor vendor = vendorRepository.findAll().stream().findFirst().get();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(
                        "/api/v1/shopping-cart/products/inventories"
                ).queryParam("inventoryId", vendor.getProducts().get(0).getProductInventory().get(0).getId().toString())
                .queryParam("vendorId", vendor.getId().toString())
                .header("Authorization", getBearerToken());
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(String.format("Stock with id %s is deleted", vendor.getProducts().get(0).getProductInventory().get(0).getId())));
    }
}
