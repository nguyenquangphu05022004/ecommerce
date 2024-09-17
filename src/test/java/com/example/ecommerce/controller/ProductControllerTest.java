package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.ProductFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.algorithm.search.product.BrandFilter;
import com.example.ecommerce.service.algorithm.search.product.NameFilter;
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
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ProductRepository productRepository;

    private List<ProductRequest> productRequests = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PasswordEncoder encoder;
    private List<ProductModelView> productModelViews = new ArrayList<>();
    private Category category;
    private ProductBrand brand;

    private List<Category> categories = new ArrayList<>();
    private List<ProductBrand> brands = new ArrayList<>();

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

        Category c1 = getCategory("Shirts");
        Category c2 = getCategory("Computers");
        Category c3 = getCategory("Sports");
        Category c4 = getCategory("Pants");

        categories.addAll(List.of(c1, c2, c3, c4));

        ProductBrand b1 = getBrand("Adidas");
        ProductBrand b2 = getBrand("Nike");
        ProductBrand b3 = getBrand("Asus");
        ProductBrand b4 = getBrand("Dell");

        brands.addAll(List.of(b1, b2, b3, b4));


        ProductRequest p1 = getProductRequest(c3.getId(), b1.getId(), "Ao the thao liverpool");
        ProductRequest p2 = getProductRequest(c3.getId(), b2.getId(), "Ao the thao liverpool");
        ProductRequest p3 = getProductRequest(c2.getId(), b3.getId(), "Laptop gaming ASUS");
        ProductRequest p4 = getProductRequest(c2.getId(), b3.getId(), "Laptop Inspire Dell 15 3000");

        productRequests.addAll(List.of(p1, p2, p3, p4));
    }

    public ProductRequest getProductRequest(Long cateId, Long brandId, String name) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setCategoryId(cateId);
        productRequest.setBrandId(brandId);
        productRequest.setDescription("asdasdasdadadadasdad");
        productRequest.setCombination(false);
        productRequest.setNameEn(name);
        productRequest.setNameVn(name);
        productRequest.setSlug(Arrays.stream(name.split("\\s+")).collect(Collectors.joining("-")));
        return productRequest;
    }

    public ProductBrand getBrand(String name) {
        ProductBrand brand = new ProductBrand();
        brand.setName(name);
        brand.setSlug(Arrays.stream(name.toLowerCase().split("\\s+")).collect(Collectors.joining("-")));
        brandRepository.save(brand);
        return brand;
    }

    public Category getCategory(String name) {
        Category category = new Category();
        category.setSlug(Arrays.stream(name.toLowerCase().split("\\s+")).collect(Collectors.joining("-")));
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

    @AfterEach
    public void destroy() {
        if (productModelViews != null) {
            productModelViews.forEach(s -> {
                productRepository.deleteById(s.getId());
            });
        }
        brands.forEach(s -> {
            brandRepository.delete(s);
        });
        categories.forEach(s -> {
            categoryRepository.delete(s);
        });
        userService.delete(this.registerRequest.getUsername());
    }

    @Test
    void createProduct() throws Exception {
        httpCreateProduct(this.productRequests.get(0));
    }

    void httpCreateProduct(ProductRequest productRequest) throws Exception {
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.post(
                                apiVersion + "/products"
                        ).header("Authorization", "Bearer " + this.authenResponse.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(productRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductModelView data = this.objectMapper.readValue(contentAsString, new TypeReference<APIResponse<ProductModelView>>() {
        }).getData();

        Assertions.assertThat(data.getSlug()).isEqualTo(productRequest.getSlug());
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
    void getAllProduct() throws Exception {
        productRequests.forEach(s -> {
            try {
                httpCreateProduct(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        ProductFilterRequest request = new ProductFilterRequest();
        Map<String, String> map = new HashMap<>();
         map.put(NameFilter.class.getSimpleName(), "ao the thao");
         map.put(BrandFilter.class.getSimpleName(), brands.get(1).getId() + "");
        request.setData(map);
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.post(
                                apiVersion + "/products/search"
                        ).header("Authorization", "Bearer " + this.authenResponse.getToken())
                        .content(this.objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        APIListResponse<ProductGalleryModelView> responses = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIListResponse<ProductGalleryModelView>>() {}
        );

        Assertions.assertThat(responses.getData().size()).isEqualTo(1);

    }
}
