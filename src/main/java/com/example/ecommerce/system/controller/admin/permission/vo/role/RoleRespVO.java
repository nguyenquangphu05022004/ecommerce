package com.example.ecommerce.system.controller.admin.permission.vo.role;

import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleRespVO {
    @Schema(description = "Ten role")
    private String name;
    @Schema(description = "Loai nguoi dung")
    private UserType userType;
    private Long id;

    public RoleRespVO(Role role) {
        this.id = role.getId();
        this.userType = role.getUserType();
        this.name = role.getName();
    }
}
