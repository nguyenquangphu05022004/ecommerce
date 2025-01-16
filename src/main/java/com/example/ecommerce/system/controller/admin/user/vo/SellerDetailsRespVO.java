package com.example.ecommerce.system.controller.admin.user.vo;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
public class SellerDetailsRespVO extends SellerResVO{
    private int numComment;
    private int numProduct;
    /**
     * Tinh theo thang
     */
    private int joined;

    private int numFollow;

    private int replyPercent;
}
