package com.example.ecommerce.system.controller.admin.mail.vo.template;

import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "Simple - MailTemplateSimpleRespVO")
@NoArgsConstructor
public class MailTemplateSimpleRespVO {
    @Schema(description = "id", example = "1")
    private Long id;

    @Schema(description = "Ten cua mau mail", example = "Quen mat khau")
    private String name;

    public MailTemplateSimpleRespVO(MailTemplate mailTemplate) {
        this.id = mailTemplate.getId();
        this.name = mailTemplate.getName();
    }
}
