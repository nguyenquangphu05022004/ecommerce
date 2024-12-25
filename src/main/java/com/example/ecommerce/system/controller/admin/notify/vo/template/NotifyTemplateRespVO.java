package com.example.ecommerce.system.controller.admin.notify.vo.template;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class NotifyTemplateRespVO {
    private Long id;
    private String name;
    private String content;
    private List<String> params;
}
