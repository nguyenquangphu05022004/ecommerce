package com.example.ecommerce.realtime.controller.admin.live.product;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.realtime.controller.admin.live.product.vo.LiveProductReqVO;
import com.example.ecommerce.realtime.controller.admin.live.product.vo.LiveProductResVO;
import com.example.ecommerce.realtime.service.live.LiveProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/realtime/live/products")
@Tag(name = "Live Product", description = "San pham khi livestream")
public class LiveProductController {

    private final LiveProductService liveProductService;

    @PostMapping
    @Operation(summary = "San pham khi live")
    @PreAuthorize("@ss.hasPermission('realtime-live-product:update')")
    public CommonResult<Boolean> createLiveProduct(@RequestBody LiveProductReqVO req) {
        liveProductService.createLiveProduct(req);
        return success(true);
    }

    @GetMapping("/livestream/{liveId}")
    @Operation(summary = "Lay danh sach san pham cua 1 phien live")
    public CommonResult<List<LiveProductResVO>> getListByStreamId(@PathVariable("liveId") Long liveId) {
        return success(convertList(liveProductService.getListLiveProductByLiveStreamId(liveId), LiveProductResVO::new));
    }

    @Operation(summary = "Hien thi san pham len man hinh")
    @PutMapping("/display")
    @PreAuthorize("@ss.hasPermission('realtime-live-product:update')")
    public CommonResult<Boolean> displayLiveProduct(@RequestParam("liveProductId") Long liveProductId,
                                                    @RequestParam("liveStreamId") Long liveStreamId) {
        liveProductService.updateLiveProductDisplay(liveProductId, liveStreamId);
        return success(true);
    }

    @Operation(summary = "Ghim san pham len khung chat")
    @PutMapping("/pin")
    @PreAuthorize("@ss.hasPermission('realtime-live-product:update')")
    public CommonResult<LiveProductResVO> updatePinLiveProduct(@RequestParam("liveProductId") Long liveProductId,
                                                               @RequestParam("pin") Boolean pin) {
        return success(liveProductService.updatePinLiveProduct(liveProductId, pin), LiveProductResVO::new);
    }

    @Operation(summary = "Xoa live product")
    @DeleteMapping("/{liveProductId}")
    @PreAuthorize("@ss.hasPermission('realtime-live-product:update')")
    public CommonResult<Boolean> deleteLiveProduct(@PathVariable("liveProductId") Long id) {
        liveProductService.deleteLiveProduct(id);
        return success(true);
    }
}
