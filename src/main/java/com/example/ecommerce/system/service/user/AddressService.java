package com.example.ecommerce.system.service.user;

import com.example.ecommerce.system.controller.app.user.vo.AddressCreateReqVO;
import com.example.ecommerce.system.controller.app.user.vo.AddressResVO;

import java.util.List;

public interface AddressService {


    AddressResVO createUserAddress(AddressCreateReqVO req);
    AddressResVO updateDefaultAddress(Long userId,Long addressId);
    List<AddressResVO> getListAddressByUser(Long userId);
    AddressResVO updateAddress(Long addressId);
}
