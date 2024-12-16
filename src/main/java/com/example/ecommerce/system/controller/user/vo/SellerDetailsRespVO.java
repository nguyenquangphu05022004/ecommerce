package com.example.ecommerce.system.controller.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.Seller;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
