package com.example.ecommerce.system.controller.mail.vo.template;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "Gui email toi    nguoi dung - MailTemplateSendReqVO")
public class MailTemplateSendReqVO {
    @NotNull
    @Schema(description = "Mau mail de gui toi customer, nhu la: Mau quen mat khau, dat hang", example = "1")
    private Long mailTemplateId;
    @NotEmpty
    @Schema(description = "Email cua nguoi dung", example = "test@gmail.com")
    private String toMail;

    @Schema(description = "Thong so mau ben trong content cua template")
    private Map<String, Object> templateParams;
}
