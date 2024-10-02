package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.*;
import com.example.ecommerce.domain.model.binding.*;
import com.example.ecommerce.domain.model.modelviews.order.OrderModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.repository.BrandRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.algorithm.search.product.CateParentFilter;
import com.example.ecommerce.service.algorithm.search.product.PriceFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
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

    private List<OrderModelView> orderModelViews = new ArrayList<>();
    private Vendor vendor;

    private List<ProductRequest> productRequests = new ArrayList<>();
    private List<ProductInventoryModelView> productInventoryModelViews = new ArrayList<>();
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PasswordEncoder encoder;
    private List<ProductModelView> productModelViews = new ArrayList<>();
    private AuthenResponse userAuthen;
    private List<ProductInventoryRequest> productInventoryRequests = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<ProductBrand> brands = new ArrayList<>();

    @BeforeEach
    void setup() throws Exception {

        userAuthen = LoginResponse.authResponse(
                apiVersion,
                mockMvc,
                objectMapper,
                1
        ).get(0);
        vendor = Vendor.builder()
                .shopName("test-shop")
                .build();
        vendorRepository.save(vendor);
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("test@gmail.com");
        registerRequest.setPassword("test_vendor");
        registerRequest.setFullName("test nguyen");
        User user = User.builder()
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

    public ProductInventoryRequest getInventoryRequest(int price, ProductModelView p1) {
        ProductInventoryRequest request = new ProductInventoryRequest();
        request.setProductId(p1.getId());
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put("Color", "Red");
        attributeMap.put("Size", "XL");
        request.setPrice(price);
        request.setQuantity(2000);
        request.setSkuCode("LIVER-XL-RED");
        return request;
    }

    void httpCreateProductInventory(ProductInventoryRequest request) throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "files",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "hello world".getBytes()
        );
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.multipart(
                                apiVersion + "/products/inventories"
                        )
                        .file(file)
                        .header("Authorization", "Bearer " + this.authenResponse.getToken())
                        .param("productInventoryRequest", this.objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("created product inventory"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        ProductInventoryModelView inventoryModelView = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIResponse<ProductInventoryModelView>>() {
                }
        ).getData();
        this.productInventoryModelViews.add(inventoryModelView);
    }

    @Test
    void create_product_inventory() throws Exception {
        productRequests.forEach(s -> {
            try {
                httpCreateProduct(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        httpCreateProductInventory(getInventoryRequest(
                2000,
                productModelViews.get(0)
        ));
        httpCreateProductInventory(getInventoryRequest(
                1500,
                productModelViews.get(1)
        ));
        httpCreateProductInventory(getInventoryRequest(
                500,
                productModelViews.get(2)
        ));
        httpCreateProductInventory(getInventoryRequest(
                200,
                productModelViews.get(3)
        ));
    }


    @Test
    void getAllProduct() throws Exception {
        this.create_product_inventory();
        ProductFilterRequest request = new ProductFilterRequest();
        Map<String, String> map = new HashMap<>();
        map.put(PriceFilter.class.getSimpleName(), "200;1500");
        map.put(CateParentFilter.class.getSimpleName(), categories.get(2).getId() + "");
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
                new TypeReference<APIListResponse<ProductGalleryModelView>>() {
                }
        );

        Assertions.assertThat(responses.getData().size()).isEqualTo(0);
    }


    /**
     * Order product
     */

    @Test
    void test_create_order() throws Exception {
        this.create_product_inventory();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setPayment(Payment.AT_HOME);
        Set<LineItemRequest> lineItemRequests = new HashSet<>();
        LineItemRequest lineItemRequest = new LineItemRequest();
        lineItemRequest.setCouponId(null);
        lineItemRequest.setVendorId(this.vendor.getId());
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setQuantity(5);
        itemRequest.setInventoryId(this.productInventoryModelViews.get(0).getId());
        lineItemRequest.setItems(new ArrayList<>(List.of(itemRequest)));
        lineItemRequests.add(lineItemRequest);
        orderRequest.setLineItems(lineItemRequests);

        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.post(
                                apiVersion + "/orders"
                        ).content(this.objectMapper.writeValueAsBytes(orderRequest))
                        .header("Authorization", "Bearer " + this.userAuthen.getToken())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("cretead order"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        OrderModelView orderModelView = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIResponse<OrderModelView>>() {
                }
        ).getData();
        this.orderModelViews.add(orderModelView);
    }


    @Test
    void test_get_orders() throws Exception {
        FilterOrderRequest filterOrderRequest = new FilterOrderRequest();
        this.test_create_order();
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.get(
                                apiVersion + "/orders"
                        ).header("Authorization", "Bearer " + this.userAuthen.getToken())
                        .content(this.objectMapper.writeValueAsBytes(filterOrderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("get all order by order state"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        APIListResponse<OrderModelView> response = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIListResponse<OrderModelView>>() {}
        );
        Assertions.assertThat(response.getData()).hasSize(1);
    }
    @Test
    void test_delete_order() throws Exception {
        this.test_create_order();
        this.mockMvc.perform(MockMvcRequestBuilders.delete(
                apiVersion + "/orders/" + this.orderModelViews.get(0).getId()
        ).header("Authorization", "Bearer " + this.userAuthen.getToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("delete order success"));
    }

}
