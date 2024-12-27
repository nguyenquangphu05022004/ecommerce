package com.example.ecommerce.system.controller.app.notify;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.controller.app.notify.vo.NotifyMessageRespVO;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import com.example.ecommerce.system.service.notify.NotifyMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app-api/system/notify/messages")
@Tag(name = "Notify Message")
public class NotifyMessageController {
    private final NotifyMessageService notifyMessageService;

    @Operation(summary = "Lay toan bo thong bao cua user hien tai, phan chia trang")
    @GetMapping("/my-notify/page")
    public CommonResult<PageResult<NotifyMessageRespVO>> getMyPageNotifyMessage(@RequestParam PageParam req) {
        PageResult<NotifyMessage> pageResult = this.notifyMessageService.getNotifyMessagePageByUserId(
                SecurityUtils.getLoginUserMemberId(),
                req
        );
        return success(pageResult, NotifyMessageRespVO::new);
    }

    @Operation(summary = "Lay thong bao theo id")
    @GetMapping("/{id}")
    public CommonResult<NotifyMessageRespVO> getNotifyMessageById(@PathVariable("id") Long id) {
        return success(notifyMessageService.getNotifyMessageByIdAndUserId(id, SecurityUtils.getLoginUserMemberId()), NotifyMessageRespVO::new);
    }

    @Operation(summary = "Xoa thong bao")
    @DeleteMapping("/{id}")
    public CommonResult<Boolean> deleteNotifyMessage(@PathVariable("id") Long id) {
        notifyMessageService.deleteNotifyMessage(id, SecurityUtils.getLoginUserMemberId());
        return success(true);
    }
}
