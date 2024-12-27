package com.example.ecommerce.system.controller.admin.permission.vo.menu;

import com.example.ecommerce.system.dal.dataobject.permission.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MenuCreateReqVO {
    @Schema(description = "Ten menu", example = "system-permission-menu:update")
    private String name;
    @Schema(description = "Loai menu", example = "BUTTON")
    private Menu.MenuType menuType;
}
