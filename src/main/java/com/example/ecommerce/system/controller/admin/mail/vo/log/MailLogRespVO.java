package com.example.ecommerce.system.controller.admin.mail.vo.log;

import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.enums.SendMailStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;

@Getter
@Schema(name = "MailLog")
public class MailLogRespVO {
    @Schema(description = "Gui tu mail")
    private String fromMail;
    @Schema(description = "Gui toi mail")
    private String toMail;
    @Schema(description = "Noi dung mail")
    private String content;
    @Schema(description = "Tieu de cua mail")
    private String title;
    @Enumerated(EnumType.STRING)
    @Schema(description = "Trang thai gui mail")
    private SendMailStatus sendMailStatus;

    public MailLogRespVO(MailLog mailLog) {
        this.fromMail = mailLog.getFromMail();
        this.toMail = mailLog.getToMail();
        this.content = mailLog.getContent();
        this.title = mailLog.getTitle();
        this.sendMailStatus = mailLog.getSendMailStatus();
    }
}
