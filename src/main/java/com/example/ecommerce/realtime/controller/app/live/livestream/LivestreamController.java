package com.example.ecommerce.realtime.controller.app.live.livestream;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.realtime.controller.admin.live.product.vo.LiveProductResVO;
import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LiveStreamCreateReqVO;
import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LivestreamDetailRespVO;
import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LivestreamRespVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import com.example.ecommerce.realtime.service.live.LiveProductService;
import com.example.ecommerce.realtime.service.live.LiveStreamService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app-api/realtime/livestreams")
public class LivestreamController {
    private final LiveStreamService liveStreamService;
    private final LiveProductService liveProductService;
    @PostMapping
    @Operation(summary = "Tạo sự kiện livestream")
    @PreAuthorize("@ss.hasPermission('realtime-livestream:update')")
    public CommonResult<LivestreamRespVO> createLivestream(@RequestBody LiveStreamCreateReqVO req) {
        LiveStream liveStream = liveStreamService.createLiveStream(req);
        return CommonResult.success(liveStream, LivestreamRespVO::new);
    }
    @GetMapping
    @Operation(summary = "Lấy danh sách livestream")
    public CommonResult<List<LivestreamRespVO>> getListLivestream() {
        return CommonResult.success(liveStreamService.getListLivestream());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy thông tin về phiên live")
    public CommonResult<LivestreamDetailRespVO> getLivestream(@PathVariable("id") Long id) {
        LiveStream liveStream = liveStreamService.getLiveStreamById(id);
        List<LiveProduct> liveProducts = liveProductService.getListLiveProductByLiveStreamId(id);

        LivestreamDetailRespVO live = new LivestreamDetailRespVO(liveStream);
        live.setLiveProducts(CollUtils.convertList(liveProducts, LiveProductResVO::new));
        return CommonResult.success(live);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa phiên live")
    @PreAuthorize("@ss.hasPermission('realtime-livestream:update')")
    public CommonResult<Boolean> deleteLivestream(@PathVariable("id") Long id) {
        liveStreamService.deleteLivestream(id);
        return CommonResult.success(true);
    }

    @Operation(summary = "Đóng phiên live")
    @PutMapping("/close/{id}")
    @PreAuthorize("@ss.hasPermission('realtime-livestream:update')")
    public CommonResult<Boolean> closeLivestream(@PathVariable("id") Long id) {
        liveStreamService.closeLiveStream(id);
        return CommonResult.success(true);
    }
}
