package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Role;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Vendor;
import com.example.ecommerce.domain.entities.Coupon;
import com.example.ecommerce.domain.model.binding.CouponRequest;
import com.example.ecommerce.domain.model.binding.VendorRequest;
import com.example.ecommerce.domain.model.modelviews.product.CouponModelView;
import com.example.ecommerce.handler.exception.CodeExpiredException;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.ResourcesNotFoundException;
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

import static com.example.ecommerce.domain.entities.EntityType.Type.VENDOR;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final PasswordEncoder encoder;


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


        vendor.getUsersFavorite().add(user);
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
                .orElseThrow(() -> new ResourcesNotFoundException("Coupon code that you enter not found"));
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
        vendor.getUsersFavorite().remove(User.builder().id(userId).build());
        vendorRepository.save(vendor);
        return apiResponse("cancel follow vendor", null);
    }

    @Override
    public void delete(Long entityId) {

    }

    public static <T> APIResponse<T> apiResponse(String message, T response) {
        return new APIResponse<T>(
                200,
                response,
                message
        );
    }
}
