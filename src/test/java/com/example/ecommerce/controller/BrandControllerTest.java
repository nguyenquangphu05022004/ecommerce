package com.example.ecommerce.controller;

import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.domain.model.modelviews.product.BrandModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.service.IBrandService;
import com.example.ecommerce.service.IUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BrandControllerTest {
    @Value("${api.version}")
    private String apiVersion;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private AuthenResponse authenResponse;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtService jwtService;
    private List<BrandRequest> brandRequests;
    @BeforeEach
    void init() throws Exception {
        brandRequests = new ArrayList<>();
        BrandRequest request = new BrandRequest();
        request.setName("Adidas");
        request.setSlug("Adidas");
        brandRequests.add(request);
        for(int i = 0; i < 10; i++) {
            BrandRequest b = new BrandRequest();
            b.setSlug(i + "A");
            b.setName(i + "A");
            brandRequests.add(b);
        }

        this.authenResponse = LoginResponse.authResponse(apiVersion, mockMvc, objectMapper, 1).get(0);
    }

    @AfterEach
    void destroy() {

        userService.delete(this.jwtService.extractUsername(this.authenResponse.getToken()));
        brandRequests.forEach(b -> {
            brandService.deleteByName(b.getName());
        });
    }

    @Test
    void test_create_brand() throws Exception {
        resultFromApi(brandRequests.get(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("you created brand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name")
                        .value(brandRequests.get(0).getName()));

    }

    private ResultActions resultFromApi(BrandRequest brandRequest) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.post(
                        this.apiVersion + "/brands"
                ).content(this.objectMapper.writeValueAsBytes(brandRequest))
                .header("Authorization", "Bearer " + this.authenResponse.getToken())
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test_create_brand_when_name_exists() throws Exception {
        test_create_brand();
        resultFromApi(brandRequests.get(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("brand name exists, you can't save it"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(400));
    }


    @Test
    void test_get_brand() throws Exception {
        brandRequests.forEach(s -> {
            try {
                resultFromApi(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get(
                                apiVersion + "/brands"
                        ).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.authenResponse.getToken()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<BrandModelView> brandModelViews = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIListResponse<BrandModelView>>() {}
        ).getData();

        assertThat(brandModelViews).hasSize(this.brandRequests.size());

        for(int i = 0; i < brandModelViews.size(); i++) {
            assertThat(brandModelViews.get(i).getName()).isEqualTo(this.brandRequests.get(i).getName());
            assertThat(brandModelViews.get(i).getSlug()).isEqualTo(this.brandRequests.get(i).getSlug().toLowerCase());
        }


    }
}
