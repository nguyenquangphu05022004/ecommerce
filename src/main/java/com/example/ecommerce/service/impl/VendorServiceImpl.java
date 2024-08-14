package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.order.Coupon;
import com.example.ecommerce.domain.model.binding.CouponRequest;
import com.example.ecommerce.domain.model.binding.VendorRequest;
import com.example.ecommerce.domain.model.modelviews.product.CouponModelView;
import com.example.ecommerce.handler.exception.CodeExpiredException;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.domain.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final PasswordEncoder encoder;

    @Override
    public APIResponse<?> saveOrUpdate(VendorRequest request) {
        Vendor vendor = Vendor.builder()
                .shopName(request.getShopName())
                .perMoneyDelivery(request.getPerMoneyDelivery())
                .build();
        vendorRepository.save(vendor);
        User user = User.builder()
                .password(encoder.encode(request.getPassword()))
                .userType(UserType.VENDOR)
                .role(Role.VENDOR)
                .fullName(request.getFullName())
                .userTypeId(vendor.getId())
                .build();
        userRepository.save(user);
        return apiResponse("create vendor", null);
    }

    @Override
    @Transactional
    public APIResponse<?> userFollow(Long vendorId) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username: %s not found", SecurityUtils.getUsername())
                ));

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new GeneralException(
                        String.format("Vendor with id %s not found", vendorId)
                ));


        vendor.getUsers().add(user);
        vendorRepository.save(vendor);
        return apiResponse("follow vendor",null);
    }

    @Override
    public APIResponse<?> createCoupon(CouponRequest request) {
        return apiResponse("create coupon", null);
    }

    @Override
    public APIResponse<?> checkCouponExpire(Long vendorId, String couponCode) {
        Coupon coupon = couponRepository.findByVendorIdAndAndCode(vendorId, couponCode)
                .orElseThrow(() -> new NotFoundException("Coupon code that you enter not found"));
        if (coupon.isExpired()) {
            throw new CodeExpiredException(String.format("Your code that you enter: %s was expired", couponCode));
        }
        CouponModelView couponResponse = new CouponModelView(coupon.getId(), coupon.getMoneyDecrease(), couponCode);
        return apiResponse("coupon verify success",couponResponse);
    }

    @Override
    public APIResponse<?> cancelFollowVendor(Long userId, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new GeneralException(String.format("Vendor with id: %s not found", vendorId)));
        vendor.getUsers().remove(User.builder().id(userId).build());
        vendorRepository.save(vendor);
        return apiResponse("cancel follow vendor", null);
    }

    public static <T> APIResponse<T> apiResponse(String message, T response) {
        return new APIResponse<T>(
                message,
                0,
                1,
                200,
                response
        );
    }
}
