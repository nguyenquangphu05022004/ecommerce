package com.example.ecommerce.system.controller.notify.vo.template;

import lombok.Data;

@Data
public class NotifyTemplateCreateReqVO extends NotifyTemplateRespVO {
    private Long id;
    private String name;
    private String content;
}
