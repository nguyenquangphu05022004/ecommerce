package com.example.ecommerce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseDto {
    private Long id;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
