package com.example.ecommerce.controller;

import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Group;
import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.binding.MessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.MessageModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.repository.GroupRepository;
import com.example.ecommerce.repository.MessageRepository;
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

import static com.example.ecommerce.domain.entities.EntityType.Type.GROUP;
import static com.example.ecommerce.domain.entities.EntityType.Type.USER;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private AuthenResponse authen1;
    private List<AuthenResponse> authenResponses;
    @Value("${api.version}")
    private String apiVersion;
    private List<MessageModelView> messageModelViews = new ArrayList<>();
    private List<MessageRequest> messageRequests = new ArrayList<>();

    @Autowired
    private GroupRepository groupRepository;
    private Group group;

    @BeforeEach
    void setUp() throws Exception {
        this.authenResponses = LoginResponse.authResponse(
                apiVersion, mockMvc, objectMapper, 3
        );
        this.authen1 = this.authenResponses.get(0);

        MessageRequest message1 = new MessageRequest();
        message1.setContent("hello world");
        message1.setToEntityType(new EntityType(USER, this.authenResponses.get(1).getUserId()));


        messageRequests.add(message1);

        MessageRequest message2 = new MessageRequest();
        message2.setContent("hello world");
        message2.setToEntityType(new EntityType(USER, this.authen1.getUserId()));

        messageRequests.add(message2);

        MessageRequest message3 = new MessageRequest();
        message3.setContent("hello world");
        message3.setToEntityType(new EntityType(USER, this.authenResponses.get(1).getUserId()));

        messageRequests.add(message3);


        this.group = new Group();
        this.group.setName("test group");
        this.group.getUsers().add(User.builder().id(authenResponses.get(0).getUserId()).build());
        this.group.getUsers().add(User.builder().id(authenResponses.get(1).getUserId()).build());

        this.groupRepository.save(this.group);

        MessageRequest m4 = new MessageRequest();
        m4.setContent("hello group");
        m4.setToEntityType(new EntityType(GROUP, this.group.getId()));

        messageRequests.add(m4);
    }


    @Test
    void createMessage() throws Exception {
        httpRequestMessage(this.messageRequests.get(0), this.authenResponses.get(0));
        httpRequestMessage(this.messageRequests.get(1), this.authenResponses.get(1));
    }

    private void httpRequestMessage(MessageRequest messageRequest,
                                    AuthenResponse fromUser) throws Exception {
        messageRequest.setFromUserId(fromUser.getUserId());
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
                        .header("Authorization", "Bearer " + fromUser.getToken()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        MessageModelView response = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIResponse<MessageModelView>>() {
                }
        ).getData();
        Assertions.assertThat(response.getContent()).isEqualTo(messageRequest.getContent());
        Assertions.assertThat(response.getDestination().getEntityType().getEntityType().name()).isEqualTo(messageRequest.getToEntityType().getEntityType().name());
        Assertions.assertThat(response.getUrlsImage()).hasSize(1);
        Assertions.assertThat(response.getFromUser().getFullName()).isEqualTo(fromUser.getFullName());
        this.messageModelViews.add(response);
    }


    @Test
    void getMessageDetails() throws Exception {
        httpRequestMessage(this.messageRequests.get(0), this.authenResponses.get(0));
        httpRequestMessage(this.messageRequests.get(1), this.authenResponses.get(1));
        httpRequestMessage(this.messageRequests.get(2), this.authenResponses.get(2));
        httpRequestMessage(this.messageRequests.get(3), this.authenResponses.get(1));

        FilterMessageRequest filter = new FilterMessageRequest();
        filter.setPage(1);
        filter.setLimit(100);
        filter.setEntityType(new EntityType(GROUP, this.group.getId()));

        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.post(
                                        apiVersion + "/messenger/messages/details"
                                ).header("Authorization", "Bearer " + this.authenResponses.get(1).getToken())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsBytes(filter))
                ).andReturn()
                .getResponse()
                .getContentAsString();

        List<MessageModelView> modelViews = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIListResponse<MessageModelView>>() {}
        ).getData();

        Assertions.assertThat(modelViews).hasSize(1);
    }

    @Test
    void getMessageGallery() throws Exception {
        httpRequestMessage(this.messageRequests.get(0), this.authenResponses.get(0));
        httpRequestMessage(this.messageRequests.get(1), this.authenResponses.get(1));
        httpRequestMessage(this.messageRequests.get(2), this.authenResponses.get(2));
        httpRequestMessage(this.messageRequests.get(3), this.authenResponses.get(1));

        String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.post(
                        apiVersion + "/messenger/messages/galleries"
                ).header("Authorization", "Bearer " + this.authenResponses.get(1).getToken()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<MessageModelView> messageModelViews = this.objectMapper.readValue(
                contentAsString,
                new TypeReference<APIListResponse<MessageModelView>>() {
                }
        ).getData();

        Assertions.assertThat(messageModelViews).hasSize(3);
        Assertions.assertThat(messageModelViews.get(0).getFromUser().getFullName()).isEqualTo(this.authenResponses.get(1).getFullName());

    }
}
