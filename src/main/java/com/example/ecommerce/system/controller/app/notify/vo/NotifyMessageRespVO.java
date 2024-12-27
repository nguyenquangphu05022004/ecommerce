package com.example.ecommerce.system.controller.app.notify.vo;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NotifyMessageRespVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "Ten thong bao")
    private String nameNotify;
    @Schema(description = "Noi dung thong bao")
    private String content;
    @Schema(description = "Trang thai doc thong bao")
    private Boolean readStatus;
    @Schema(description = "Thoi gian doc")
    private String readTime;

    public NotifyMessageRespVO(NotifyMessage notifyMessage) {
        this.id = notifyMessage.getId();
        this.content = notifyMessage.getContent();
        this.readStatus = notifyMessage.getReadStatus();
        this.readTime = DateTimeUtils.format(notifyMessage.getReadTime());
    }

}
