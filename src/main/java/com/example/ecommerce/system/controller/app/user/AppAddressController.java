package com.example.ecommerce.system.controller.app.user;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.controller.app.user.vo.AddressCreateReqVO;
import com.example.ecommerce.system.controller.app.user.vo.AddressResVO;
import com.example.ecommerce.system.service.user.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/system/user/addresses")
public class AppAddressController {

    private final AddressService addressService;

    @PostMapping
    @Operation(summary = "Tao dia chi lien he user")
    public CommonResult<AddressResVO> createAddress(@RequestBody AddressCreateReqVO req) {
        AddressResVO userAddress = addressService.createUserAddress(req);
        return CommonResult.success(userAddress);
    }

    @GetMapping
    @Operation(summary = "lay danh sach dia chi user")
    public CommonResult<List<AddressResVO>> getListAddress() {
        return CommonResult.success(addressService.getListAddressByUser(SecurityUtils.getLoginUserMemberId()));
    }



}
