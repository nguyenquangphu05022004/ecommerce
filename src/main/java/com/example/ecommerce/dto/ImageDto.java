package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto extends BaseDto{
    private String name;
    private String shortUrl;
}
