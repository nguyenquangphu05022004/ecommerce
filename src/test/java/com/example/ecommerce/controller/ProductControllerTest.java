package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.AuthenRequest;
import com.example.ecommerce.domain.model.binding.FilterProductRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.algorithm.search.ProductFilterType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ecommerce.domain.entities.EntityType.Type.VENDOR;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IUserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    private AuthenResponse authenResponse;
    @Autowired
    private VendorRepository vendorRepository;
    @Value("${api.version}")
    private String apiVersion;
    private RegisterRequest registerRequest;
    private Vendor vendor;
    private ProductRequest productRequest;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PasswordEncoder encoder;
    private List<ProductModelView> productModelViews = new ArrayList<>();
    private Category category;
    private ProductBrand brand;
    @BeforeEach
    void setup() throws Exception {
        vendor = Vendor.builder()
                .shopName("test-shop")
                .build();
        vendorRepository.save(vendor);
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("test@gmail.com");
        registerRequest.setPassword("test_vendor");
        registerRequest.setFullName("test nguyen");
        User user = User.builder()
                .entityType(new EntityType(VENDOR, vendor.getId()))
                .username(registerRequest.getUsername())
                .password(encoder.encode(registerRequest.getPassword()))
                .fullName(registerRequest.getFullName())
                .birthOfDate(LocalDateTime.now())
                .role(Role.VENDOR)
                .build();
        this.userRepository.save(user);

        authenResponse = LoginResponse.authResponse(
                registerRequest,
                objectMapper,
                mockMvc,
                apiVersion,
                true
        );
         category = new Category();
        category.setSlug("test");
        category.setName("test");
        categoryRepository.save(category);

         brand = new ProductBrand();
        brand.setName("test");
        brand.setSlug("test");
        brandRepository.save(brand);

        productRequest = new ProductRequest();
        productRequest.setCategoryId(category.getId());
        productRequest.setBrandId(brand.getId());
        productRequest.setDescription("asdasdasdadadadasdad");
        productRequest.setCombination(false);
        productRequest.setLanguage(new Product.Language("test product", "test product"));
        productRequest.setSlug("test product");
    }

    @AfterEach
    public void destroy() {
        productModelViews.forEach(s -> {
            productRepository.deleteById(s.getId());
        });
        brandRepository.delete(brand);
        categoryRepository.delete(category);
        userService.delete(this.registerRequest.getUsername());
    }

    @Test
    void createProduct() throws Exception {
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.post(
                                apiVersion + "/products"
                        ).header("Authorization", "Bearer " + this.authenResponse.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(this.productRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductModelView data = this.objectMapper.readValue(contentAsString, new TypeReference<APIResponse<ProductModelView>>() {
        }).getData();

        Assertions.assertThat(data.getSlug()).isEqualTo(this.productRequest.getSlug());
        this.productModelViews.add(data);
    }

    @Test
    void getProductById() throws Exception {
        createProduct();
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.get(
                        apiVersion + "/products/" + this.productModelViews.get(0).getId() + "/slug"
                ).header("Authorization", "Bearer " + this.authenResponse.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDetailsViewModel data = this.objectMapper.readValue(contentAsString, new TypeReference<APIResponse<ProductDetailsViewModel>>() {
        }).getData();
        Assertions.assertThat(this.productModelViews.get(0).getId()).isEqualTo(data.getId());
    }

    @Test
    void getAllProductRecommendation() {
    }

    @Test
    void getAllProduct() {
        FilterProductRequest request = new FilterProductRequest();
        Map<ProductFilterType, String> map = new HashMap<>();
    }
}
