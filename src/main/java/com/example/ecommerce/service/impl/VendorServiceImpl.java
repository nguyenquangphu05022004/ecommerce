package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.CustomStatusCode;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.order.Coupon;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.handler.exception.CodeExpiredException;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.service.dto.VendorDto;
import com.example.ecommerce.service.request.CouponRequest;
import com.example.ecommerce.service.request.VendorRequest;
import com.example.ecommerce.service.response.APIResponse;
import com.example.ecommerce.service.response.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;



    @Override
    public void saveOrUpdate(VendorRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username: %s not found", request.getUsername())
                ));
        user.setRole(Role.VENDOR);
    }

//    @Override
//    public List<VendorResponseInbox> findAllByName(String vendorName) {
//        String username = SecurityUtils.username();
//        System.out.println("Username: " + username);
//        List<Vendor> vendors = vendorRepository
//                .findAllByVendorNameAndNotMySelf(vendorName, username);
//        List<VendorResponseInbox> vendorResponses = vendors.stream().map(vendor -> {
//            List<ConversationResponse> conversationsConnectedBetweenVendorAndUser
//                    = vendor.getUser()
//                    .getConversations()
//                    .stream()
//                    .filter(conversation -> conversation.getUsers()
//                            .stream()
//                            .anyMatch(user -> user.getUsername().equals(username)))
//                    .map(conversation -> ConversationMapper.mapperToConversationResponse(conversation, username))
//                    .collect(Collectors.toList());
//            return VendorResponseInbox.builder()
//                    .user(
//                            UserInboxResponse.builder()
//                                    .conversationResponses(
//                                            conversationsConnectedBetweenVendorAndUser.isEmpty()
//                                                    ? null
//                                                    : conversationsConnectedBetweenVendorAndUser
//                                    )
//                                    .fullName(vendor.getUser()
//                                            .getUserContactDetails() != null
//                                            ?
//                                            vendor.getUser()
//                                                    .getUserContactDetails()
//                                                    .getFullName()
//                                            : "ANONYMOUS")
//                                    .username(vendor.getUser().getUsername())
//                                    .active(UserTrack.getInstance().getMap().get(
//                                            vendor.getUser().getUsername() != null
//                                    ))
//                                    .build()
//                    )
//                    .shopName(vendor.getShopName())
//                    .id(vendor.getId())
//                    .build();
//        }).toList();
//        return vendorResponses;
//    }

    @Override
    @Transactional
    public void userFollow(Long vendorId) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.username())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username: %s not found", SecurityUtils.username())
                ));

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new GeneralException(
                        String.format("Vendor with id %s not found", vendorId)
                ));


        vendor.getUsers().add(user);
        vendorRepository.save(vendor);

    }

    @Override
    public void createCoupon(CouponRequest request) {

    }

    @Override
    public APIResponse<CouponResponse> checkCouponExpire(Long vendorId, String couponCode) {
        Coupon coupon = couponRepository.findByVendorIdAndAndCode(vendorId, couponCode)
                .orElseThrow(() -> new NotFoundException("Coupon code that you enter not found"));
        if(coupon.isExpired()) {
            throw new CodeExpiredException(String.format("Your code that you enter: %s was expired", couponCode));
        }
        CouponResponse couponResponse = CouponResponse.builder()
                .id(coupon.getId())
                .decreaseMoney(coupon.getMoneyDecrease())
                .code(couponCode)
                .build();
        return new APIResponse<>(
                "OK",
                "NULL",
                1,
                CustomStatusCode.SUCCESS.getNumber(),
                couponResponse
        );
    }

    @Override
    public void cancelFollowVendor(Long userId, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new GeneralException(String.format("Vendor with id: %s not found", vendorId)));
        vendor.getUsers().remove(User.builder().id(userId).build());
        vendorRepository.save(vendor);
    }
}
