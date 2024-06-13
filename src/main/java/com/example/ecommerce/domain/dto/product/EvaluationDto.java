package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EvaluationDto extends BaseDto {
    private Integer rating;
    private String content;
    private UserResponseInfo user;
    private List<ImageDto> images =new ArrayList<>();

    public String getFormatDate(){
        return SystemUtils.getFormatDate(getModifiedDate(), "dd-MM-yyyy HH:mm:ss");
    }
}
