package com.example.ecommerce.system.controller.admin.mail;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.controller.admin.mail.vo.log.MailLogRespVO;
import com.example.ecommerce.system.controller.admin.mail.vo.template.MailTemplateCreateReqVO;
import com.example.ecommerce.system.controller.admin.mail.vo.template.MailTemplateRespVO;
import com.example.ecommerce.system.controller.admin.mail.vo.template.MailTemplateSendReqVO;
import com.example.ecommerce.system.controller.admin.mail.vo.template.MailTemplateSimpleRespVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.service.mail.MailSendService;
import com.example.ecommerce.system.service.mail.MailTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/mail/templates")
@Tag(name = "Mail Template")
public class MailTemplateController {
    private final MailTemplateService mailTemplateService;
    private final MailSendService mailSendService;
    @PostMapping
    @Operation(summary = "Tao mau mail")
    @PreAuthorize("@ss.hasPermission('system-mail-template:create')")
    public CommonResult<MailTemplateRespVO> createMailTemplate(@RequestBody MailTemplateCreateReqVO req) {
        return success(this.mailTemplateService.createMailTemplate(req), MailTemplateRespVO::new);
    }

    @GetMapping
    @Operation(summary = "Lay toan bo mau mail")
    @PreAuthorize("@ss.hasPermission('system-mail-template:get-all')")
    public CommonResult<List<MailTemplateSimpleRespVO>> getListMailTemplate() {
        return success(CollUtils.convertList(mailTemplateService.getListMailTemplate(), MailTemplateRespVO::new));
    }

    @Operation(summary = "Lay mau mail theo id")
    @GetMapping("/{templateId}")
    @PreAuthorize("@ss.hasPermission('system-mail-template:get-by-id')")
    public CommonResult<MailTemplateRespVO> getMailTemplateById(@PathVariable("templateId") Long templateId) {
        return success(this.mailTemplateService.getMailTemplateById(templateId), MailTemplateRespVO::new);
    }


    @Operation(summary = "Gui mail")
    @GetMapping("/send-mail")
    @PreAuthorize("@ss.hasPermission('system-mail-template:send-mail')")
    public CommonResult<MailLogRespVO> sendMail(@RequestBody MailTemplateSendReqVO req) {
        MailLog mailLog = this.mailSendService.sendSingleMail(req.getToMail(), SecurityUtils.getLoginUserMemberId(),
                req.getMailTemplateId(), req.getTemplateParams());
        return success(mailLog,MailLogRespVO::new);
    }

}
