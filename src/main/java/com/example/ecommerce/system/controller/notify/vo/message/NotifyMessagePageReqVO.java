package com.example.ecommerce.system.controller.notify.vo.message;

import com.example.ecommerce.frame.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Phan trang NotifyMessage Request")
@Data
public class NotifyMessagePageReqVO extends PageParam {
    @Schema(description = "Id nguoi dung")
    private Long userId;
    @Schema(description = "Id mau thong bao")
    private Long notifyTemplateId;

}
