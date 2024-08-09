package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.model.binding.EvaluationRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.evaluation.EvaluationDetailsModelView;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class EvaluationControllerTest {

    private AuthenResponse authenResponse;
    @Autowired
    private IAuthenService authenService;
    @Autowired private UserRepository userRepository;
    @Autowired private TokenRepository tokenRepository;
    @Autowired private EvaluationRepository evaluationRepository;
    @Autowired private EvaluationImageRepository evaluationImageRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired private VendorRepository vendorRepository;
   @Autowired private PasswordEncoder passwordEncoder;
    @BeforeEach
    public void initLogin() throws Exception {
        this.login();
    }
    @AfterEach
    public void destroy() {
        evaluationImageRepository.deleteAll();
        evaluationRepository.deleteAll();
        tokenRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        vendorRepository.deleteAll();
    }

    private void saveProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setBrandId(1l);
        request.setCategoryId(2l);
        request.setDescription("hello worldrkwejwejfkdjglsdfksldgkdsogerscsfsafcsaca");
        request.setPrice(2500);
        request.setCombination(false);
        request.setLanguage(new Product.Language("Ao the liverpool", "ao the thao liverpool"));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/products"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void create_evaluation_test_success() throws Exception {
        this.saveProduct();


        Product product = productRepository.findAll()
                .stream().findFirst()
                .orElse(null);
        EvaluationRequest request = new EvaluationRequest();
        request.setContent("hello world, xin chao");
        request.setRating(4);
        request.setProductId(product.getId());


////        MockMvc mockMvc
//                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        mockMvc.perform(multipart("/upload").file(file))
//                .andExpect(status().isOk());
        MockMultipartFile firstFile = new MockMultipartFile(
                "files",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "hello, world".getBytes()
        );
        MockMultipartFile secondFile = new MockMultipartFile(
                "files",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "hello, world".getBytes()
        );
        MockMultipartFile requestEvaluation = new MockMultipartFile(
                "evaluationRequest",
                "",
                "application/json",
                objectMapper.writeValueAsString(request).getBytes()
        );
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .multipart("/api/v1/products/evaluations")
                .file(firstFile)
                .file(secondFile)
                .file(requestEvaluation)
                .header("Authorization", "Bearer " + this.authenResponse.getToken());
        String data = mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        EvaluationDetailsModelView response = objectMapper.readValue(data, EvaluationDetailsModelView.class);
        assertThat(request.getContent())
                .isEqualTo(response.getContent());
        assertThat(response.getChildren().isEmpty()).isTrue();
        assertThat(response.getImageUrls().size()).isEqualTo(2);

    }

    @Test
    void delete_test() throws Exception {
        create_evaluation_test_success();
        Evaluation evaluation = evaluationRepository.findAll().stream().findFirst().orElse(null);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(
                "/api/v1/products/evaluations/" + evaluation.getId()
        ).header("Authorization", "Bearer " + this.authenResponse.getToken());

        mockMvc.perform(builder).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("delete success"));

        List<Evaluation.EvaluationImage> images = evaluationImageRepository.findAllByEvaluationId(evaluation.getId());

        assertThat(images.size()).isEqualTo(0);

    }


    void login() {
        Vendor vendor = Vendor.builder()
                .shopName("test")
                .perMoneyDelivery(1555)
                .build();
        vendorRepository.save(vendor);
        User user = User.builder()
                .username("quangphu2050@gmail.com")
                .password(passwordEncoder.encode("mahiru"))
                .role(Role.VENDOR)
                .userType(UserType.VENDOR)
                .userTypeId(vendor.getId())
                .build();
        userRepository.save(user);
        AuthenRequest login = new AuthenRequest();
        login.setPassword("mahiru");
        login.setUsername(user.getUsername());
        this.authenResponse = this.authenService.authenticate(login);
    }

}
