package com.example.ecommerce.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Class
public class EmailDetails {
    private String recipient;
    private String subject;
    private String content;

    public String getFormatContent() {
        return String.format("<h3>Code verify: %s</h3>", content);
    }
}
