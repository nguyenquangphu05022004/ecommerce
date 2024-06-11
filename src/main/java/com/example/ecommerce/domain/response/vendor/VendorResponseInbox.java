package com.example.ecommerce.domain.response.vendor;

import com.example.ecommerce.domain.response.ConversationResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VendorResponseInbox {
    private Long id;
    private String shopName;
    private List<ConversationResponse> conversationResponses;
    private String username;
}
