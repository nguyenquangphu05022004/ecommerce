package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.MessageRequest;
import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.MessageModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;

public interface IMessageService {

    APIResponse<MessageModelView> createMessage(MessageRequest request);

    APIListResponse<MessageModelView> getMessages(FilterMessageRequest request);

    APIListResponse<MessageModelView> getMessageGallery(int page, int limit);
}
