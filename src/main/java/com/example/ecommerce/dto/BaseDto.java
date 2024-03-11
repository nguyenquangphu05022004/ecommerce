package com.example.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Data
@NoArgsConstructor
public abstract class BaseDto {
    private Long id;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
