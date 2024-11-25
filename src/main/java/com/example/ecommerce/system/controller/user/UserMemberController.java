package com.example.ecommerce.system.controller.user;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.system.controller.user.vo.UserMemberCreateReqVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberResVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberUpdatePasswordReqVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.user.UserMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.*;
import static com.example.ecommerce.frame.security.core.utils.SecurityUtils.*;

@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserMemberController {
    private final UserMemberService userMemberService;

    @PostMapping
    @PermitAll
    @Operation(summary = "Create new account")
    public CommonResult<Boolean> createUserMember(@RequestBody UserMemberCreateReqVO reqVO) {
        this.userMemberService.createUser(reqVO);
        return success(true);
    }

    @PutMapping("/info")
    @Operation(summary = "Update info of user")
    public CommonResult<UserMemberResVO> updateProfile(@RequestBody UserMemberUpdateReqVO reqVO) {
        UserMember userMember = userMemberService.updateUser(getLoginUserMemberId(), reqVO);
        return success(userMember, UserMemberResVO::new);
    }

    @PutMapping("/password")
    @Operation(summary = "Update user password")
    public CommonResult<Boolean> updatePassword(@RequestBody UserMemberUpdatePasswordReqVO reqVO) {
        this.userMemberService.updatePassword(getLoginUserMemberId(), reqVO);
        return success(true);
    }

    @GetMapping
    @Operation(summary = "Get profile account user")
    public CommonResult<UserMemberResVO> getProfileUser() {
        UserMember userMember = userMemberService.getUserMemberProfile(getLoginUserMemberId());
        return success(userMember, UserMemberResVO::new);
    }

    @PutMapping
    @Operation(summary = "Update status account user")
    @PreAuthorize("@ss.hasPermission('sys:user:update-status-account')")
    @OperationLog
    public CommonResult<Boolean> updateStatusAccountUser(@PathVariable("userId") Long userId,
                                            @RequestParam("locked") boolean locked) {
        this.userMemberService.updateStatusAccount(userId, locked);
        return success(true);
    }
}
