package com.example.ecommerce.system.controller.admin.mail;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.system.controller.admin.mail.vo.account.MailAccountCreateReqVO;
import com.example.ecommerce.system.controller.admin.mail.vo.account.MailAccountRespVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import com.example.ecommerce.system.service.mail.MailAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.*;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/mail/accounts")
@Tag(name = "Mail Account")
public class MailAccountController {
    private final MailAccountService mailAccountService;

    @PreAuthorize("@ss.hasPermission('system-mail-account:create')")
    @PostMapping
    @Operation(summary = "Tao tai khoan email ung dung cho nguoi ban hang, he thong")
    public CommonResult<MailAccountRespVO> createMailAccount(@RequestBody MailAccountCreateReqVO req) {
        MailAccount mailAccount = mailAccountService.createMailAccount(req);
        return success(mailAccount, MailAccountRespVO::new);
    }

    @PreAuthorize("@ss.hasPermission('system-mail-account:get-all)")
    @GetMapping
    @Operation(summary = "Lay danh sach tai khoan mail")
    public CommonResult<List<MailAccountRespVO>> getListMailAccount() {
        return success(convertList(this.mailAccountService.getListMailAccount(), MailAccountRespVO::new));
    }
}
