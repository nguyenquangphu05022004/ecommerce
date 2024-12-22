package com.example.ecommerce.frame.common.validate.user;

import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.USER_WAS_NOT_SELLER;

public class UserUtils {
    public static void isCustomer() {

    }
    public static void isSeller(UserMember userMember) {
        if(userMember instanceof Seller) return;

        throw exception(USER_WAS_NOT_SELLER);
    }
}
