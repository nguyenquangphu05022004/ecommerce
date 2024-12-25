package com.example.ecommerce.system.controller.admin.user.vo;

import lombok.*;

@Getter
@NoArgsConstructor
public class SellerDetailsRespVO extends SellerResVO{
    private Integer numComment;
    private Integer numProduct;
    /**
     * Tinh theo thang
     */
    private Integer joined;

    private Integer numFollow;

    private Integer replyPercent;
}
