package com.example.ecommerce.system.service.user;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.user.vo.SellerCreateReqVO;
import com.example.ecommerce.system.controller.user.vo.SellerDetailsRespVO;
import com.example.ecommerce.system.dal.dataobject.user.Seller;

import java.util.List;

public interface SellerService {
    Seller createSeller(SellerCreateReqVO reqVO);
    PageResult<Seller> getListSeller(int page);
    SellerDetailsRespVO getSellerDetails(Long sellerId);
    Seller getSellerByUseMemberId(Long userMemberId);
}
