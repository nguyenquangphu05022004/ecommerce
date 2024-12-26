package com.example.ecommerce.system.controller.admin.logger;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.logger.vo.operation.OperationLoggerRespVO;
import com.example.ecommerce.system.controller.admin.logger.vo.operation.PageOperationLoggerReqVO;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import com.example.ecommerce.system.service.logger.OperationLoggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/logger/operations")
@Tag(name = "Operation Logger")
public class OperationLoggerController {
    private final OperationLoggerService operationLoggerService;

    @PreAuthorize("@ss.hasPermission('system-logger-operation:get")
    @GetMapping("/page")
    @Operation(summary = "Lay toan bo ban ghi ve thao tac cua user, phan chia trang")
    public CommonResult<PageResult<OperationLoggerRespVO>> getPageOperationLog(@RequestBody PageOperationLoggerReqVO req) {
        PageResult<OperationLogger> pageOperationLogger = this.operationLoggerService.getPageOperationLog(req);
        return CommonResult.success(pageOperationLogger, OperationLoggerRespVO::new);
    }

    @PreAuthorize("@ss.hasPermission('system-logger-operation:get'")
    @GetMapping
    @Operation(summary = "Lay toan bo ban ghi ve thao tac cua user")
    public CommonResult<List<OperationLoggerRespVO>> getListOperation() {
        List<OperationLoggerRespVO> list = CollUtils.convertList(operationLoggerService.getListOperationLog(), OperationLoggerRespVO::new);
        return CommonResult.success(list);
    }

    @PreAuthorize("@ss.hasPermission('system-logger-operation:delete'")
    @DeleteMapping
    @Operation(summary = "Xoa ban ghi ve thao tac cua user")
    public CommonResult<Boolean> deleteLog(@RequestBody Collection<Long> ids) {
        this.operationLoggerService.clear(ids);
        return CommonResult.success(true);
    }
}
