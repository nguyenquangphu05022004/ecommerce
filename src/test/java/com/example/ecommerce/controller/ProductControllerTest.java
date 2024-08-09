package com.example.ecommerce.controller;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.InventoryRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.request.KeySearchRequest;
import com.example.ecommerce.service.response.APIListResponse;
import com.example.ecommerce.service.response.AuthenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    private AuthenResponse authenResponse;
    @Autowired private ProductCacheRepository productCacheRepository;
    @Autowired private ProductSimilarityRepository productSimilarityRepository;
    @BeforeEach
    public void init() {
        Vendor vendor = Vendor.builder()
                .shopName("test")
                .perMoneyDelivery(1555)
                .build();
        vendorRepository.save(vendor);
        User user = User.builder()
                .username("quangphu2050@gmail.com")
                .password(passwordEncoder.encode("mahiru"))
                .role(Role.USER)
                .userType(UserType.CUSTOMER)
                .build();

        User vendor1 = User.builder()
                .username("vendor")
                .password(passwordEncoder.encode("mahiru"))
                .role(Role.VENDOR)
                .userType(UserType.VENDOR)
                .userTypeId(vendor.getId())
                .build();

        userRepository.save(user);
        userRepository.save(vendor1);
    }

    @AfterEach
    public void destroy() {
        tokenRepository.deleteAll();
        productSimilarityRepository.deleteAll();
        productCacheRepository.deleteAll();
        userRepository.deleteAll();
        inventoryRepository.deleteAll();
        productRepository.deleteAll();
        languageRepository.deleteAll();
        vendorRepository.deleteAll();
    }

    @Test
    void test_create_product() throws Exception {
        ProductRequest request = getProductRequest();

        performTestProductCreate(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(request.getLanguage().getNameVn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value(request.getDescription()));
    }

    private ResultActions performTestProductCreate(
            ProductRequest request
    ) throws Exception {
        if (this.authenResponse == null) {
            vendorLogin();
        }
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/products"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        return mockMvc.perform(builder);
    }

    private ProductRequest getProductRequest() {
        ProductRequest request = new ProductRequest();
        request.setBrandId(1l);
        request.setCategoryId(2l);
        request.setDescription("hello worldrkwejwejfkdjglsdfksldgkdsogerscsfsafcsaca");
        request.setPrice(2500);
        request.setCombination(false);
        request.setLanguage(new Product.Language("Ao the liverpool", "ao the thao liverpool"));
        return request;
    }

    public void initListProduct() throws Exception {
        ProductRequest v1 = getProductRequest();
        v1.setCategoryId(2l);
        v1.setBrandId(1l);
        v1.setPrice(3500);
        ProductRequest v2 = getProductRequest();
        v2.setLanguage(new Product.Language("giay theo thao snaker", "giay the thao snaker"));
        v2.setCategoryId(2l);
        v2.setBrandId(2l);
        v2.setPrice(2345);
        ProductRequest v3 = getProductRequest();
        v3.setBrandId(2l);
        v3.setPrice(3593);
        v3.setLanguage(new Product.Language("quan jun", "quan jun"));
        v3.setCategoryId(2l);

        ProductRequest v4 = getProductRequest();
        v4.setLanguage(new Product.Language("ao the thao man united", "ao the thao man united"));
        v4.setPrice(3000);
        v4.setBrandId(1l);

        ProductRequest v5 = getProductRequest();
        v5.setLanguage(new Product.Language("ao the thao barca jean", "ao the thao barca jean"));
        v5.setBrandId(1l);
        v5.setPrice(2300);

        performTestProductCreate(v1);
        performTestProductCreate(v2);
        performTestProductCreate(v3);
        performTestProductCreate(v4);
        performTestProductCreate(v5);
    }

    @Test
    void test_user_not_vendor() throws Exception {
        ProductRequest request = getProductRequest();
        customerLogin();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/products"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void test_get_product_by_id() throws Exception {
        initListProduct();
        List<Product> products = productRepository.findAll();
        Long productId = products.get(3).getId();
        String slug = Arrays.stream(products.get(3).getLanguage().getNameEn().split("\\s+"))
                .collect(Collectors.joining("_"));

        MockHttpServletRequestBuilder builder = findProductByIdRequest(String.format("/api/v1/products/%s/%s", productId, slug));

        String productJson = mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse().getContentAsString();
        ProductDetailsViewModel productDetailsViewModel = objectMapper.readValue(productJson, ProductDetailsViewModel.class);

        Thread.sleep(2000);


        //test recommendation
        MockHttpServletRequestBuilder requestRecommendation = MockMvcRequestBuilders.get(
                String.format("/api/v1/products/%s/recommendation", productId)
        ).contentType("application/json")
                .header("Authorization","Bearer " + this.authenResponse.getToken());

        String data = mockMvc.perform(requestRecommendation).andReturn().getResponse()
                .getContentAsString();
        APIListResponse<ProductGalleryModelView> apiResponses =
                objectMapper.readValue(
                        data,
                        APIListResponse.class);
        assertThat(apiResponses.getStatus())
                .isEqualTo(200);
        assertThat(apiResponses.getLimit())
                .isEqualTo(-1);
        assertThat(apiResponses.getMessage())
                .isEqualTo("ao the thao barca jean");
    }

    private MockHttpServletRequestBuilder findProductByIdRequest(String url) {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                        url
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken());
        return builder;
    }


    @Test
    void getAllProduct() throws Exception {
        initListProduct();
        Map<String, String> keys = new HashMap<>();
        keys.put(KeySearchRequest.CATEGORY_PARENT_ID.name(), String.valueOf(1l));
        keys.put(KeySearchRequest.BRAND_ID.name(), String.valueOf(1l));
//        keys.put(KeySearchRequest.PRODUCT_NAME.name(), "ao the thao");
        keys.put(KeySearchRequest.PRICE.name(), "2000;3000");
        FilterInputRequestProduct request =
                FilterInputRequestProduct.builder()
                        .mapKey(keys)
                        .build();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/products/search"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));
        APIListResponse<ProductGalleryModelView> productResponses
                = objectMapper.readValue(mockMvc.perform(builder)
                        .andReturn().getResponse()
                        .getContentAsString(),
                APIListResponse.class);
        assertThat(productResponses.getData())
                .hasSize(2);
    }

    @Test
    void getInventoryAttributeKey() throws Exception {
        ProductRequest request = getProductRequest();
        vendorLogin();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/products"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        MvcResult result = mockMvc.perform(builder).andReturn();
        ProductDetailsViewModel product = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                ProductDetailsViewModel.class
        );

        ProductInventory inventory = inventoryRepository.save(
                ProductInventory.builder()
                        .attributeCombinationKey(String.format(
                                "Color:Red%sSize:45%sRam:8GB", SystemUtils.SEPARATE, SystemUtils.SEPARATE
                        ))
                        .numberOfProductSold(0)
                        .skuCode("red_pro")
                        .quantity(5)
                        .product(Product.builder().id(product.getId()).build())
                        .build()
        );

        inventoryRepository.save(inventory);

        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setProductId(product.getId());
        inventoryRequest.setAttributeCombinationKey(String.format(
                "Color:Red%sSize:45%sRam:8GB", SystemUtils.SEPARATE, SystemUtils.SEPARATE
        ));
        MockHttpServletRequestBuilder httpRequest = MockMvcRequestBuilders.get(
                        "/api/v1/products/inventories"
                ).header("Authorization", "Bearer " + this.authenResponse.getToken())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inventoryRequest));
        mockMvc.perform(httpRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value(inventory.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attributeCombinationProduct")
                        .value(String.format(
                                "Color:Red%sSize:45%sRam:8GB", ", ", ", "
                        )));

        //test findById
        MockHttpServletRequestBuilder test = findProductByIdRequest(String.format("/api/v1/products/%s/%s", product.getId(), "test"));
        String testData = mockMvc.perform(test).andReturn().getResponse().getContentAsString();
        ProductDetailsViewModel productDetailsViewModel = objectMapper.readValue(testData, ProductDetailsViewModel.class);

        assertThat(productDetailsViewModel.getName()).isEqualTo(product.getName());


    }

    private void vendorLogin() throws Exception {
        AuthenRequest login = new AuthenRequest();
        login.setUsername("vendor");
        login.setPassword("mahiru");
        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(login));
        String requestResponse = mockMvc.perform(b1).andReturn().getResponse().getContentAsString();
        AuthenResponse authenResponse = objectMapper.readValue(requestResponse, AuthenResponse.class);
        this.authenResponse = authenResponse;
    }

    private void customerLogin() throws Exception {
        AuthenRequest login = new AuthenRequest();
        login.setUsername("quangphu2050@gmail.com");
        login.setPassword("mahiru");
        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(login));
        String requestResponse = mockMvc.perform(b1).andReturn().getResponse().getContentAsString();
        AuthenResponse authenResponse = objectMapper.readValue(requestResponse, AuthenResponse.class);
        this.authenResponse = authenResponse;
    }
}
