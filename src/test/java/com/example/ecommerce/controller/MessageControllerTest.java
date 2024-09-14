package com.example.ecommerce.controller;

import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.model.binding.MessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.MessageModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.repository.MessageRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IUserService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.example.ecommerce.domain.entities.EntityType.Type.USER;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {


    private MessageRequest messageRequest;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private AuthenResponse authenResponse;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    private User userTest;
    @Value("${api.version}")
    private String apiVersion;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtService jwtService;
    private List<MessageModelView> messageModelViews = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
        this.authenResponse = AuthResponse.authResponse(
                apiVersion, mockMvc, objectMapper
        );

        userTest = new User();
        userTest.setPassword("19219291912");
        userTest.setUsername("quduuwrqwurqwrquwr");

        userRepository.save(userTest);

        this.messageRequest = new MessageRequest();
        messageRequest.setContent("hello world");
        messageRequest.setFromUserId(this.authenResponse.getEntityType().getEntityId());
        messageRequest.setToEntityType(new EntityType(USER, userTest.getId()));
    }

    @AfterEach
    void tearDown() {
        this.messageModelViews.forEach(s -> {
            this.messageRepository.deleteById(s.getId());
        });
        this.userRepository.deleteById(userTest.getId());
        this.userService.delete(jwtService.extractUsername(authenResponse.getToken()));
    }

    @Test
    void createMessage() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "files",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "hello".getBytes()
        );
        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.multipart(
                                apiVersion + "/messenger/chat"
                        )
                        .file(file)
                        .param("messageRequest", this.objectMapper.writeValueAsString(messageRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.authenResponse.getToken()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        MessageModelView response = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIResponse<MessageModelView>>() {}
        ).getData();
        Assertions.assertThat(response.getContent()).isEqualTo(this.messageRequest.getContent());
        Assertions.assertThat(response.getDestination().getEntityType().getEntityType().name()).isEqualTo(USER.name());
        Assertions.assertThat(response.getUrlsImage()).hasSize(1);
        this.messageModelViews.add(response);
    }

    @Test
    void getMessageDetails() {
    }

    @Test
    void getMessageGallery() {
    }
}
