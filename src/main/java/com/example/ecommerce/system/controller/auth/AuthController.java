package com.example.ecommerce.system.controller.auth;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.system.controller.auth.vo.AuthLoginReqVO;
import com.example.ecommerce.system.controller.auth.vo.AuthLoginResVO;
import com.example.ecommerce.system.service.authen.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
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
}
