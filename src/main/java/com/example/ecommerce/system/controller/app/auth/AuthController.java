package com.example.ecommerce.system.controller.app.auth;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.servlet.ServletUtils;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.controller.app.auth.vo.AuthLoginReqVO;
import com.example.ecommerce.system.controller.app.auth.vo.AuthLoginResVO;
import com.example.ecommerce.system.service.authen.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RequiredArgsConstructor
@RequestMapping("/app-api/system/auth")
@RestController
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Dang nhap")
    @PermitAll
    public CommonResult<AuthLoginResVO> login(@RequestBody AuthLoginReqVO reqVO) {
        return CommonResult.success(authService.login(reqVO));
    }

    @GetMapping("/refresh-token")
    @Operation(summary = "Refresh token")
    @PermitAll
    public CommonResult<AuthLoginResVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        AuthLoginResVO authLoginResVO = authService.refreshToken(refreshToken);
        return CommonResult.success(authLoginResVO);
    }

    @GetMapping
    @Operation(summary = "Logout")
    public CommonResult<Boolean> logout(HttpServletRequest req) {
        String accessToken = SecurityUtils.obtainToken(req);
        authService.logout(accessToken);
        return CommonResult.success(true);
    }

}
