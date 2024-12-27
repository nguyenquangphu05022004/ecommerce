package com.example.ecommerce.system.controller.admin.notify.vo.template;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class NotifyTemplateRespVO extends NotifyTemplateSimpleRespVO{
    @Schema(description = "Noi dung thong bao")
    private String content;
    @Schema(description = "Thong so mau")
    private List<String> params;

    public NotifyTemplateRespVO(NotifyTemplate template) {
        super(template);
        this.content = template.getContent();
        this.params = template.getParams();
    }
}
