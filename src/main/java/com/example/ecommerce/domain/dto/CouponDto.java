package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CouponDto extends BaseDto {
    private String code;
    private Integer percent;
    private String content;
    private LocalDateTime start;
    private LocalDateTime end;
    public VendorResponseCoupon vendor;
    private boolean isExpired;
    //check current day is event start
    public String getFormatStart() {
        return SystemUtils.getFormatDate(start, "dd-MM-yyyy HH:mm:ss ");
    }
    public String getFormatEnd() {
        return SystemUtils.getFormatDate(end, "dd-MM-yyyy HH:mm:ss");
    }
    @Getter
    @Setter
    public static class VendorResponseCoupon {
        private Long id;
        private String shopName;
    }
}
