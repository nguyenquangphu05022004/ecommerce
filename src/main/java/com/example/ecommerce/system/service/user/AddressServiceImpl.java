package com.example.ecommerce.system.service.user;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.object.ObjectUtils;
import com.example.ecommerce.system.controller.app.user.vo.AddressCreateReqVO;
import com.example.ecommerce.system.controller.app.user.vo.AddressResVO;
import com.example.ecommerce.system.dal.dataobject.user.Address;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.AddressRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final UserMemberRepository userMemberRepository;
    @Override
    public AddressResVO createUserAddress(AddressCreateReqVO req) {
        Address address = Address.builder()
                .user(UserMember.builder().id(req.getUserMemberId()).build())
                .detailAddress(req.getDetailAddress())
                .city(req.getCity()).district(req.getDistrict()).commune(req.getCommune())
                .defaultAddress(req.getDefaultAddress())
                .fullName(req.getFullName())
                .phoneNumber(req.getPhoneNumber())
                .build();
        addressRepository.save(address);
        return new AddressResVO(address);
    }

    @Override
    public AddressResVO updateDefaultAddress(Long userId, Long addressId) {
        List<Address> addresses = addressRepository.findAllByUserId(userId);
        AtomicReference<Address> defaultAddress = new AtomicReference<>();
        CollUtils.convertList(addresses, address -> {
            if(address.getId().equals(addressId)) {
                address.setDefaultAddress(true);
                defaultAddress.set(address);
            } else {
                address.setDefaultAddress(false);
            }
            return null;
        });
        this.addressRepository.saveAll(addresses);
        return ObjectUtils.get(defaultAddress.get(), AddressResVO::new);
    }

    @Override
    public List<AddressResVO> getListAddressByUser(Long userId) {
        return CollUtils.convertList(addressRepository.findAllByUserId(userId), AddressResVO::new);
    }

    @Override
    public AddressResVO updateAddress(Long addressId) {
        return null;
    }
}
