package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.ChatMessageRequest;
import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageDetailModelView;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageGalleryModelView;
import com.example.ecommerce.domain.response.APIListResponse;

public interface IChatMessageService {

    APIListResponse<ChatMessageGalleryModelView> createMessage(ChatMessageRequest request);

    APIListResponse<ChatMessageGalleryModelView> getMessages(
            FilterMessageRequest request,
            int page, int limit
    );
    APIListResponse<ChatMessageGalleryModelView> getMessageGallery(int page, int limit);
}
