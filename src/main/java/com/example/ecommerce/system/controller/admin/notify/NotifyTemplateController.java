package com.example.ecommerce.system.controller.admin.notify;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.system.controller.admin.notify.vo.template.NotifyTemplateCreateReqVO;
import com.example.ecommerce.system.controller.admin.notify.vo.template.NotifyTemplateRespVO;
import com.example.ecommerce.system.controller.admin.notify.vo.template.NotifyTemplateSimpleRespVO;
import com.example.ecommerce.system.service.notify.NotifyTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/notify/templates")
@Tag(name = "Notify Template")
public class NotifyTemplateController {
    private final NotifyTemplateService notifyTemplateService;

    @PostMapping
    @Operation(summary = "Tao mau thong bao")
    @PreAuthorize("@ss.hasPermission('system-notify-template:update')")
    public CommonResult<NotifyTemplateRespVO> createNotifyTemplate(@RequestBody NotifyTemplateCreateReqVO req) {
        return success(notifyTemplateService.createNotifyTemplate(req), NotifyTemplateRespVO::new);
    }

    @PutMapping
    @Operation(summary = "Tao mau thong bao")
    @PreAuthorize("@ss.hasPermission('system-notify-template:update')")
    public CommonResult<NotifyTemplateRespVO> updateNotifyTemplate(@RequestBody NotifyTemplateCreateReqVO req) {
        return success(notifyTemplateService.updateNotifyTemplate(req), NotifyTemplateRespVO::new);
    }


    @GetMapping
    @Operation(summary = "Lay toan bo mau thong bao")
    @PreAuthorize("@ss.hasPermission('system-notify-template:get')")
    public CommonResult<List<NotifyTemplateSimpleRespVO>> getListNotifyTemplate() {
        return success(CollUtils.convertList(notifyTemplateService.getListNotifyTemplate(), NotifyTemplateSimpleRespVO::new));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lay chi tiet mau thong bao")
    @PreAuthorize("@ss.hasPermission('system-notify-template:get')")
    public CommonResult<NotifyTemplateRespVO> getNotifyTemplateById(@PathVariable("id") Long id) {
        return success(notifyTemplateService.getNotifyTemplateById(id), NotifyTemplateRespVO::new);
    }


}
