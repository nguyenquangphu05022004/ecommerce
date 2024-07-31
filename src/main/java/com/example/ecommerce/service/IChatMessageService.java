package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.ChatMessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageViewModel;
import com.example.ecommerce.service.response.APIListResponse;

import java.util.List;

public interface IChatMessageService {

    ChatMessageViewModel createMessage(ChatMessageRequest request);

    APIListResponse<ChatMessageViewModel> getMessages(ChatMessageRequest request,
                                                      int page, int limit);
}
