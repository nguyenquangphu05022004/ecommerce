package com.example.ecommerce.system.controller.admin.user;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.system.controller.admin.user.vo.PageUserReqVO;
import com.example.ecommerce.system.controller.admin.user.vo.SellerCreateReqVO;
import com.example.ecommerce.system.controller.app.user.vo.UserMemberResVO;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.user.UserMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@Tag(name = "ADMIN_USER")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/users")
public class UserMemberController {
    private final UserMemberService userMemberService;


    @PostMapping("/sellers")
    @Operation(summary = "create seller")
    @PreAuthorize("@ss.hasPermission('system-user:create-seller')")
    public CommonResult<Boolean> createSeller(@RequestBody SellerCreateReqVO reqVO) {
        this.userMemberService.createUserSeller(reqVO);
        return success(true);
    }

    @GetMapping
    @Operation(summary = "Lay danh sach user")
    @PreAuthorize("@ss.hasPermission('system-user:get-user')")
    public CommonResult<PageResult<UserMemberResVO>> getPageUser(PageUserReqVO req) {
        PageResult<UserMember> pageResult = userMemberService.getPageUser(req);
        return success(pageResult, UserMemberResVO::new);
    }



    @PutMapping
    @Operation(summary = "Update status account user")
    @PreAuthorize("@ss.hasPermission('system-user:lock-user')")
    @OperationLog
    public CommonResult<Boolean> updateStatusAccountUser(@PathVariable("userId") Long userId,
                                            @RequestParam("locked") boolean locked) {
        this.userMemberService.updateStatusAccount(userId, locked);
        return success(true);
    }
}
