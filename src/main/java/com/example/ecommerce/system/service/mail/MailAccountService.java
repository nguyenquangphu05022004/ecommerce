package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.controller.mail.vo.account.MailAccountCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;

public interface MailAccountService {
    /**
     * Moi admin(seller) account se co 1 tai khoan email,
     * de gui toi cac khach hang
     * @param reqVO
     * @return
     */
    MailAccount createMailAccount(MailAccountCreateReqVO reqVO);
}
