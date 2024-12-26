package com.example.ecommerce.system.controller.admin.mail.vo.template;

import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Schema(name = "Mau noi dung mail - MailTemplateRespVO")
public class MailTemplateRespVO extends MailTemplateSimpleRespVO{


    @Schema(description = "Noi dung cua mail khi chuyen di")
    private String content;

    @Schema(description = "Thong so mau trong phan noi dung cua mail")
    private List<String> params;

    @Schema(description = "Tieu de cua mail")
    private String title;

    public MailTemplateRespVO(MailTemplate mailTemplate) {
        super(mailTemplate);
        this.content = mailTemplate.getContent();
        this.params = mailTemplate.getParams();
        this.title = mailTemplate.getTitle();
    }
}
