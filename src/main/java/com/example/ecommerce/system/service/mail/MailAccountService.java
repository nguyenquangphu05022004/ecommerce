package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.controller.admin.mail.vo.account.MailAccountCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;

import java.util.List;

public interface MailAccountService {
    /**
     * Moi admin(seller) account se co 1 tai khoan email,
     * de gui toi cac khach hang
     * @param reqVO
     * @return
     */
    MailAccount createMailAccount(MailAccountCreateReqVO reqVO);
    MailAccount updateMailAccount(MailAccountCreateReqVO reqVO);
    MailAccount getMailAccountByUserId(Long userId); //createdBy-owner
    MailAccount getMailAccountByUsername(String username);
    List<MailAccount> getListMailAccount();
}
