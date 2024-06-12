package com.example.ecommerce.domain.dto.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendorResponseInbox {
    private Long id;
    private String shopName;
    private UserInboxResponse user;
}
