package com.example.ecommerce.system.service.user;

import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.user.vo.*;
import com.example.ecommerce.system.controller.app.user.vo.*;
import com.example.ecommerce.system.dal.dataobject.user.Customer;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.CustomerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMemberServiceImpl implements UserMemberService{
    private final UserMemberRepository userMemberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public UserMember createUserMember(CustomerCreateReqVO reqVO) {
        UserMember customer = new Customer();
        setUserMember(customer, reqVO);
        this.userMemberRepository.save(customer);
        return customer;
    }

    @Override
    public UserMember createUserSeller(SellerCreateReqVO reqVO) {
        UserMember seller = new Seller();
        setUserMember(seller, reqVO);
        ((Seller) seller).setShopImage(reqVO.getShopName());
        ((Seller) seller).setShopName(reqVO.getShopName());
        this.userMemberRepository.save(seller);
        return seller;
    }

    private void setUserMember(UserMember userMember, UserMemberCreateReqVO reqVO) {
        userMember.setLocked(false); userMember.setEmail(reqVO.getEmail());
        userMember.setSex(reqVO.getSex()); userMember.setFirstName(reqVO.getFirstName());
        userMember.setLastName(reqVO.getLastName()); userMember.setUsername(reqVO.getUsername());
        userMember.setPhoneNumber(reqVO.getPhoneNumber());
        userMember.setPassword(passwordEncoder.encode(reqVO.getPassword()));
    }

    @Override
    public UserMember updateUser(Long userId, UserMemberUpdateReqVO reqVO) {
        UserMember userMember = this.getUserMemberById(userId).toBuilder().email(reqVO.getEmail())
                .lastName(reqVO.getLastName()).firstName(reqVO.getFirstName())
                .phoneNumber(reqVO.getPhoneNumber()).sex(reqVO.getSex())
                .build();
        return this.userMemberRepository.save(userMember);
    }

    @Override
    public UserMember updatePassword(Long userId, UserMemberUpdatePasswordReqVO reqVO) {
        UserMember userMember = this.getUserMemberById(userId);
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
    public UserMember getUserMemberById(Long userId) {
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
        UserMember userMember = getUserMemberById(userId).toBuilder().locked(locked).build();
        this.userMemberRepository.save(userMember);
    }

    @Override
    public void updateUserOnline(String username, boolean isOnline) {
        UserMember userMember = getUserMemberByUsername(username);
        userMember.setOnline(isOnline);
        this.userMemberRepository.save(userMember);
    }

    @Override
    public PageResult<UserMember> getPageUser(PageUserReqVO req) {
        Specification<UserMember> spec = (root, query, builder) -> {
            Predicate predicate = null;
            if(req.getStart() != null && req.getEnd() != null) {
                predicate = andPredicate(
                        predicate,
                        builder.between(root.get("createdDate"), req.getStart(), req.getEnd()),
                        builder);
            }
            if(req.getLocked() != null) {
                predicate = andPredicate(predicate, builder.equal(root.get("locked"), req.getLocked()), builder);
            }
            return predicate;
        };
        return new PageResult<>(userMemberRepository.findAll(spec, req.buildPageRequest()));
    }


    private Predicate andPredicate(Predicate p1, Predicate p2, CriteriaBuilder c) {
        if(p1 == null) {
            return p2;
        }
        return c.and(p1, p2);
    }


@PostConstruct
    void initAdminUser() {
       if(userMemberRepository.findByUsernameIgnoreCase("admin").isEmpty()) {
           UserMember userMember = UserMember.builder()
                   .sex(UserMember.Sex.MALE)
                   .email("admin@gmail.com").locked(false)
                   .firstName("admin").lastName("admin")
                   .username("admin").password(passwordEncoder.encode("admin"))
                   .build();
           userMemberRepository.save(userMember);
       }
    }
}
