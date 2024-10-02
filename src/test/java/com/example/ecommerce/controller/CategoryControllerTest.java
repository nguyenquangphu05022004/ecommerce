package com.example.ecommerce.controller;

import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.domain.model.modelviews.product.CategoryModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.service.ICategoryService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private CategoryRequest categoryRequest;
    private  CategoryRequest categoryChildRequest;
    private AuthenResponse authenResponse;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${api.version}")
    private String apiVersion;

    @BeforeEach
    void init() throws Exception {
        categoryRequest = new CategoryRequest();
        categoryRequest.setName("Fashion");
        categoryRequest.setSlug("fashion");
        authenResponse = LoginResponse.authResponse(
                apiVersion,
                mockMvc,
                objectMapper,
                1
        ).get(0);
        categoryChildRequest = new CategoryRequest();
        categoryChildRequest.setName("T-Shirt");
        categoryChildRequest.setSlug("t-shirt");
    }



    @Test
    void create_category() throws Exception {
       this.httpRequestUpdateCategory(this.categoryRequest);
    }


    void httpRequestUpdateCategory(CategoryRequest categoryRequest) throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "hello world".getBytes()
        );
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.multipart(
                                        apiVersion + "/categories"
                                ).file(file)
                                .param("categoryRequest", this.objectMapper.writeValueAsString(categoryRequest))
                                .header("Authorization", "Bearer " + authenResponse.getToken())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name")
                        .value(categoryRequest.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.slug")
                        .value(categoryRequest.getSlug()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.urlImage")
                        .isString()).andReturn()
                .getResponse()
                .getContentAsString();

        CategoryModelView categoryModelView = objectMapper.readValue(contentAsString, new TypeReference<APIResponse<CategoryModelView>>() {})
                .getData();
        categoryRequest.setId(categoryModelView.getId());
    }




    @Test
    void test_update_category() throws Exception {
        this.httpRequestUpdateCategory(this.categoryRequest);
        this.categoryRequest.setName("Computer");
        this.categoryRequest.setSlug("computer");
        this.httpRequestUpdateCategory(this.categoryRequest);
    }


    @Test
    void getAllCategoryParent() throws Exception {
        this.httpRequestUpdateCategory(this.categoryRequest);
        this.categoryChildRequest.setParentId(this.categoryRequest.getId());
        this.httpRequestUpdateCategory(this.categoryChildRequest);
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.get(
                        apiVersion + "/categories"
                )).andReturn()
                .getResponse()
                .getContentAsString();

        APIListResponse<CategoryModelView> apiResponses = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIListResponse<CategoryModelView>>() {}
        );

        CategoryModelView categoryModelView = apiResponses.getData().get(0);
        assertThat(apiResponses.getData()).hasSize(1);
        assertThat(categoryModelView.getCategoryModelViews()).hasSize(1);


    }
}
