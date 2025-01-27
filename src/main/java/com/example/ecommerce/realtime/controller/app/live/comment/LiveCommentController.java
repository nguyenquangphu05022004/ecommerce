package com.example.ecommerce.realtime.controller.app.live.comment;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.realtime.controller.app.live.comment.vo.LiveCommentCreateReqVO;
import com.example.ecommerce.realtime.controller.app.live.comment.vo.LiveCommentRespVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveComment;
import com.example.ecommerce.realtime.service.live.LiveCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app-api/realtime/livestream/comments")
public class LiveCommentController {
    private final LiveCommentService liveCommentService;

    @PostMapping
    @Operation(summary = "Tạo comment khi đang xem livestream")
    public CommonResult<LiveCommentRespVO> createComment(@RequestBody LiveCommentCreateReqVO req) {
        LiveComment comment = liveCommentService.createComment(req);
        return CommonResult.success(comment,LiveCommentRespVO::new );
    }

    @GetMapping("/livestream/{id}")
    @Operation(summary = "Lấy danh sách comment của phiên live")
    public CommonResult<List<LiveCommentRespVO>> getListComment(@PathVariable("id") Long liveStreamId) {
        List<LiveComment> liveStream = liveCommentService.getListCommentByLiveStream(liveStreamId);
        return CommonResult.success(CollUtils.convertList(liveStream, LiveCommentRespVO::new));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa comment")
    public CommonResult<Boolean> deleteComment(@PathVariable("id") Long commentId) {
        liveCommentService.deleteMessage(commentId);
        return CommonResult.success(true);
    }

    @PutMapping("/pin")
    @Operation(summary = "Ghim comment")
    public CommonResult<Boolean> pinComment(@RequestParam("commentId") Long commentId,
                                            @RequestParam("isPin") Boolean isPin) {
        liveCommentService.pinComment(commentId, isPin);
        return CommonResult.success(true);
    }

}
