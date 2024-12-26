package com.example.ecommerce.finance.controller.admin.wallet;

import com.example.ecommerce.finance.controller.admin.wallet.vo.PageWalletReqVO;
import com.example.ecommerce.finance.controller.admin.wallet.vo.WalletRespVO;
import com.example.ecommerce.finance.dal.dataobject.wallet.Wallet;
import com.example.ecommerce.finance.service.wallet.WalletService;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/finance/wallets")
public class WalletController {
    private final WalletService walletService;

    /**
     * Permission: finance-wallet:get-page
     * @param req
     * @return
     */
    @GetMapping
    @PreAuthorize("@ss.hasPermission('finance-wallet:get-page')")
    @Operation(summary = "Lay toan bo thong tin ve vi cua user")
    public CommonResult<PageResult<WalletRespVO>> getPageWallet(@RequestBody PageWalletReqVO req) {
        PageResult<Wallet> pageResult = this.walletService.getPageWallet(req);
        return CommonResult.success(pageResult, WalletRespVO::new);
    }

    @GetMapping("/my-wallet")
    @Operation(summary = "Lay thong tin ve vi cua user hien tai")
    public CommonResult<WalletRespVO> getMyWallet() {
        Wallet wallet = this.walletService.getWalletByUserId(SecurityUtils.getLoginUserMemberId());
        return CommonResult.success(wallet, WalletRespVO::new);
    }

}
