package com.example.ecommerce.system.service.user;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.common.pojo.PagingLimitation;
import com.example.ecommerce.system.controller.user.vo.SellerCreateReqVO;
import com.example.ecommerce.system.controller.user.vo.SellerDetailsRespVO;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final SellerRepository sellerRepository;
    private final UserMemberRepository userMemberRepository;

    @Override
    public PageResult<Seller> getListSeller(int page) {
        return new PageResult<>(this.sellerRepository.findAll(PageRequest.of(page - 1, PagingLimitation.SELLER_LIMIT)));
    }

    @Override
    public SellerDetailsRespVO getSellerDetails(Long sellerId) {
            return null;
//
//        return SellerDetailsRespVO.builder()
//                .id().joined().numComment().numFollow().numProduct()
//                .shopImage().shopName().replyPercent().build();
    }

    @Override
    public Seller getSellerByUseMemberId(Long userMemberId) {
        return null;
    }
}
