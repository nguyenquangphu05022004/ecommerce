package com.example.ecommerce.system.controller.admin.logger;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.logger.vo.operation.OperationLoggerRespVO;
import com.example.ecommerce.system.controller.admin.logger.vo.operation.PageOperationLoggerReqVO;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import com.example.ecommerce.system.service.logger.OperationLoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/operation-logs")
public class OperationLoggerController {
    private final OperationLoggerService operationLoggerService;

    @GetMapping("/get-page")
    public CommonResult<PageResult<OperationLoggerRespVO>> getPageOperationLog(@RequestBody PageOperationLoggerReqVO req) {
        PageResult<OperationLogger> pageOperationLogger = this.operationLoggerService.getPageOperationLog(req);
        return CommonResult.success(pageOperationLogger, OperationLoggerRespVO::new);
    }

    @GetMapping("/get")
    public CommonResult<List<OperationLoggerRespVO>> getListOperation() {
        List<OperationLoggerRespVO> list = CollUtils.convertList(operationLoggerService.getListOperationLog(), OperationLoggerRespVO::new);
        return CommonResult.success(list);
    }

    @DeleteMapping("/delete")
    public CommonResult<Boolean> deleteLog(@RequestBody Collection<Long> ids) {
        this.operationLoggerService.clear(ids);
        return CommonResult.success(true);
    }
}
