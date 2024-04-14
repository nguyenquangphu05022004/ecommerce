package com.example.ecommerce.dto;

import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
public class CouponDto extends BaseDto {
    private String code;
    private Integer percent;
    private String content;
    //time event start
    private LocalDateTime start;
    private LocalDateTime end;
    private VendorDto vendor;
    private Boolean expired;
    //check current day is event start
    private Boolean started;
    public String getFormatStart() {
        return SystemUtils.getFormatDate(start, "HH:mm:ss dd-MM-yyyy");
    }
    public String getFormatEnd() {
        return SystemUtils.getFormatDate(end, "HH:mm:ss dd-MM-yyyy");
    }
}
