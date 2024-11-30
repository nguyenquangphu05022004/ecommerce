package com.example.ecommerce.system.service.user;

import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.system.controller.user.vo.UserMemberCreateReqVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberUpdatePasswordReqVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.user.Customer;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.CustomerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.*;

@Service
@RequiredArgsConstructor
public class UserMemberServiceImpl implements UserMemberService{
    private final CustomerRepository customerRepository;
    private final UserMemberRepository userMemberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public UserMember createUser(UserMemberCreateReqVO reqVO) {
        UserMember userMember = UserMember.builder().email(reqVO.getEmail()).sex(reqVO.getSex())
                .firstName(reqVO.getFirstName()).lastName(reqVO.getLastName())
                .username(reqVO.getUsername()).phoneNumber(reqVO.getPhoneNumber())
                .password(passwordEncoder.encode(reqVO.getPassword())).locked(false).build();
        this.userMemberRepository.save(userMember);
        Customer customer = Customer.builder().userMember(userMember).build();
        this.customerRepository.save(customer);
        return userMember;
    }

    @Override
    public UserMember updateUser(Long userId, UserMemberUpdateReqVO reqVO) {
        UserMember userMember = this.getUserMemberProfile(userId).toBuilder().email(reqVO.getEmail())
                .lastName(reqVO.getLastName()).firstName(reqVO.getFirstName())
                .phoneNumber(reqVO.getPhoneNumber()).sex(reqVO.getSex())
                .build();
        return this.userMemberRepository.save(userMember);
    }

    @Override
    public UserMember updatePassword(Long userId, UserMemberUpdatePasswordReqVO reqVO) {
        UserMember userMember = this.getUserMemberProfile(userId);
        if(!isPasswordMatch(reqVO.getOldPassword(), userMember.getPassword())) {
            throw exception(PASSWORD_NOT_FOUND);
        }
        userMember.setPassword(passwordEncoder.encode(reqVO.getNewPassword()));
        return this.userMemberRepository.save(userMember);
    }

    @Override
    public boolean isPasswordMatch(String raw, String encode) {
        return this.passwordEncoder.matches(raw, encode);
    }

    @Override
    public UserMember getUserMemberProfile(Long userId) {
        return this.userMemberRepository.findById(userId)
                .orElseThrow(() -> exception(USER_NOT_FOUND));
    }

    @Override
    public UserMember getUserMemberByUsername(String username) {
        return this.userMemberRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> exception(USERNAME_NOT_FOUND));
    }

    @Override
    public void updateStatusAccount(Long userId, boolean locked) {
        UserMember userMember = getUserMemberProfile(userId).toBuilder().locked(locked).build();
        this.userMemberRepository.save(userMember);
    }
}
