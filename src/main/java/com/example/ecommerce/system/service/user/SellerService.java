package com.example.ecommerce.system.service.user;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.user.vo.SellerDetailsRespVO;
import com.example.ecommerce.system.dal.dataobject.user.Seller;

public interface SellerService {
    PageResult<Seller> getListSeller(int page);
    SellerDetailsRespVO getSellerDetails(Long sellerId);
    Seller getSellerByUseMemberId(Long userMemberId);
}
