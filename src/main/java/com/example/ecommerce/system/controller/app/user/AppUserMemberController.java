package com.example.ecommerce.system.controller.app.user;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.controller.admin.user.vo.SellerDetailsRespVO;
import com.example.ecommerce.system.controller.app.user.vo.*;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.user.UserMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;
import static com.example.ecommerce.frame.security.core.utils.SecurityUtils.getLoginUserMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/system/users")
@Tag(name = "APP_USER")
public class AppUserMemberController {

    private final UserMemberService userMemberService;

    @PostMapping
    @PermitAll
    @Operation(summary = "Tao moi tai khoan")
    public CommonResult<Boolean> createUserMember(@RequestBody CustomerCreateReqVO reqVO) {
        this.userMemberService.createUserMember(reqVO);
        return success(true);
    }



    @PutMapping("/profile")
    @Operation(summary = "Cap nhap thong tin")
    public CommonResult<UserMemberResVO> updateProfile(@RequestBody UserMemberUpdateReqVO reqVO) {
        UserMember userMember = userMemberService.updateUser(getLoginUserMemberId(), reqVO);
        return success(userMember, UserMemberResVO::new);
    }

    @PutMapping("/password")
    @Operation(summary = "Doi mat khau")
    public CommonResult<Boolean> updatePassword(@RequestBody UserMemberUpdatePasswordReqVO reqVO) {
        this.userMemberService.updatePassword(getLoginUserMemberId(), reqVO);
        return success(true);
    }

    @GetMapping("/profile")
    @Operation(summary = "Lay thong tin")
    public CommonResult<UserMemberResVO> getProfileUser() {
        UserMember userMember = userMemberService.getUserMemberById(getLoginUserMemberId());
        return success(userMember, UserMemberResVO::new);
    }



}
