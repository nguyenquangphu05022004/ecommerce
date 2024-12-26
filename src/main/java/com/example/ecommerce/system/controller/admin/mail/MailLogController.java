package com.example.ecommerce.system.controller.admin.mail;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.mail.vo.log.MailLogRespVO;
import com.example.ecommerce.system.controller.admin.mail.vo.log.PageMailLogReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.service.mail.MailLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/mail/logs")
@Tag(name = "Mail Log")
public class MailLogController {

    private final MailLogService mailLogService;

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system-mail-log:get-page')")
    @Operation(summary = "Lay toan bo cac ban ghi da gui qua mail, phan chia trang")
    public CommonResult<PageResult<MailLogRespVO>> getPageMailLog(@RequestBody PageMailLogReqVO req) {
        return success(mailLogService.getPageMailLog(req), MailLogRespVO::new);
    }

    @GetMapping("/my-mail-log/page")
    @PreAuthorize("@ss.hasPermission('system-mail-log:get-my-page')")
    @Operation(summary = "Lay cac ban ghi ma user hien tai da gui qua mail, phan chia trang")
    public CommonResult<PageResult<MailLogRespVO>> getMyPageMailLog(@RequestBody PageMailLogReqVO req) {
        return success(mailLogService.getPageMailLog(req), MailLogRespVO::new);
    }

    @DeleteMapping
    @PreAuthorize("@ss.hasPermission('system-mail-log:delete')")
    @Operation(summary = "Xoa mail log")
    public CommonResult<Boolean> deleteMailLog(@RequestBody Collection<Long> ids) {
        mailLogService.deleteMailLog(ids);
        return success(true);
    }

}
