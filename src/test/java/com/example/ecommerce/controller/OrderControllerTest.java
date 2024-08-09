package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.auth.*;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.entities.order.Payment;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.order.ItemRequest;
import com.example.ecommerce.domain.model.binding.order.LineItemRequest;
import com.example.ecommerce.domain.model.binding.order.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired private PasswordEncoder encoder;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private LineItemRepository lineItemRepository;
    @Autowired private ItemRepository itemRepository;
     private AuthenResponse authenResponse;
    @BeforeEach
    public void init() throws Exception {
        Vendor vendor = Vendor.builder()
                .shopName("hello world")
                .perMoneyDelivery(5000)
                .build();
        vendorRepository.save(vendor);

        Customer customer = Customer.builder()
                .address(null)
                .build();
        customerRepository.save(customer);

        User userVendor = User.builder()
                .userType(UserType.VENDOR)
                .userTypeId(vendor.getId())
                .role(Role.VENDOR)
                .password(encoder.encode("mahiru"))
                .username("mahiru")
                .fullName("quang phu")
                .build();
        User userCustomer = User.builder()
                .userType(UserType.CUSTOMER)
                .userTypeId(customer.getId())
                .role(Role.USER)
                .password(encoder.encode("test"))
                .username("test")
                .fullName("test")
                .build();
        userRepository.save(userCustomer);
        userRepository.save(userVendor);

        Product product = Product.builder()
                .productBrand(ProductBrand.builder().id(1l).build())
                .category(Category.builder().id(2l).build())
                .price(500)
                .description("hello everybody today i will test my order controller")
                .language(new Product.Language("ao vietnam", "ao vietnam"))
                .combination(true)
                .slug("ao-vietnam")
                .vendor(vendor)
                .build();
        productRepository.save(product);

        ProductInventory inventory = ProductInventory.builder()
                .quantity(2)
                .product(product)
                .skuCode(product.getSlug() + "-red-xl")
                .attributeCombinationKey("Color:Red-_-Size:XL")
                .build();
        inventoryRepository.save(inventory);


        AuthenRequest authenRequest = new AuthenRequest();
        authenRequest.setUsername("test");
        authenRequest.setPassword("test");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(authenRequest));
        String response = mockMvc.perform(builder).andReturn().getResponse().getContentAsString();
        this.authenResponse = objectMapper.readValue(response, AuthenResponse.class);
    }


    @AfterEach
    public void destroy() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
        itemRepository.deleteAll();
        inventoryRepository.deleteAll();
        productRepository.deleteAll();
        lineItemRepository.deleteAll();
        vendorRepository.deleteAll();
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    public OrderRequest getOrderRequest(int quantity) {
        Vendor vendor = vendorRepository.findAll().stream().findFirst().orElse(null);
        OrderRequest orderRequest = OrderRequest.builder()
                .payment(Payment.PAY_AT_HOME)
                .lineItems(Set.of(LineItemRequest.builder()
                        .vendorId(vendor.getId())
                        .items(List.of(ItemRequest.builder()
                                .inventoryId(vendor.getProducts().get(0).getProductInventory().stream().findFirst().get().getId())
                                .quantity(quantity)
                                .build()))
                        .build()))
                .build();
        return orderRequest;
    }

    @Test
    void test_create_order_when_user_not_login() throws Exception {
        String request = objectMapper.writeValueAsString(getOrderRequest(50));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/orders"
                ).contentType("application/json")
                .content(request);

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void test_create_order_when_user_login_but_quantity_order_request_out_of_bound() throws Exception {
        String request = objectMapper.writeValueAsString(getOrderRequest(50));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/orders"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .content(request);

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Item out of bound inventory"));
    }
    @Test
    void test_create_order_success() throws Exception {
        String request = objectMapper.writeValueAsString(getOrderRequest(1));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/orders"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .content(request);

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("cretead order"));
    }

    @Test
    void get_all_order_of_customer() throws Exception {

        test_create_order_success();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                "/api/v1/orders/customer"
        ).queryParam("orderStatus", OrderStatus.NOT_APPROVAL.name())
                .header("Authorization", "Bearer " + this.authenResponse.getToken());

        String response = mockMvc.perform(builder)
                .andReturn().getResponse().getContentAsString();
        List<OrderViewModel> orderViewModels = objectMapper.readValue(response, new TypeReference<List<OrderViewModel>>() {});

        Assertions.assertThat(orderViewModels).hasSize(1);

    }

    @Test
    void test_delete_order_by_id() throws Exception {

        test_create_order_success();

        Order order = orderRepository.findAll().stream().findFirst().orElse(null);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(
                "/api/v1/orders/" + order.getId()
        ).header("Authorization", bearerToken());

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("delete order success"));
    }

    public String bearerToken() {
        return "Bearer " + this.authenResponse.getToken();
    }

}
