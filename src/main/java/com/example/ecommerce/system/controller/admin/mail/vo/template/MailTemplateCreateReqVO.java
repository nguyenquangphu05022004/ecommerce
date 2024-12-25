package com.example.ecommerce.system.controller.admin.mail.vo.template;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MailTemplateCreateReqVO {
    @NotEmpty(message = "Ten mau khong duoc trong")
    private String name;
    @NotEmpty(message = "Tieu de khong duoc trong")
    private String title;
    @NotEmpty(message = "Noi dung khong duoc trong")
    private String content;
}
