package com.example.ecommerce.system.controller.admin.notify.vo.template;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NotifyTemplateSimpleRespVO {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "Ten mau thong bao")
    private String name;

    public NotifyTemplateSimpleRespVO(NotifyTemplate template) {
        this.id = template.getId();
        this.name = template.getName();
    }
}
