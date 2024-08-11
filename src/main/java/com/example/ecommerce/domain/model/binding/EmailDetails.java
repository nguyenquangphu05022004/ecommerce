package com.example.ecommerce.domain.model.binding;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
    private String recipient;
    private String subject;
    private String content;
}
