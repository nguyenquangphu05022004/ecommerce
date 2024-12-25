package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.controller.admin.mail.vo.account.MailAccountCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import com.example.ecommerce.system.dal.repository.mail.MailAccountRepository;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.MAIL_ACCOUNT_NOT_FOUND;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.USER_HAS_NOT_CREATEd_MAIL_ACCOUNT;

@Service
@RequiredArgsConstructor
public class MailAccountServiceImpl implements MailAccountService{
    private final MailAccountRepository mailAccountRepository;
    private final UserMemberService userMemberService;
    @Override
    public MailAccount createMailAccount(Long userId, MailAccountCreateReqVO reqVO) {
        MailAccount mailAccount = MailAccount.builder().username(reqVO.getUsername())
                .auth(reqVO.getAuth()).port(reqVO.getPort()).host(reqVO.getHost())
                .password(reqVO.getPassword()).starttlsEnable(reqVO.getStarttlsEnable())
                .userMember(this.userMemberService.getUserMemberById(userId))
                .build();
        this.mailAccountRepository.save(mailAccount);
        return mailAccount;
    }

    @Override
    public MailAccount updateMailAccount(Long userId, MailAccountCreateReqVO reqVO) {
        MailAccount mailAccount = getMailAccountByUserId(userId);
        mailAccount = mailAccount.toBuilder().username(reqVO.getUsername())
                .auth(reqVO.getAuth()).port(reqVO.getPort()).host(reqVO.getHost())
                .password(reqVO.getPassword()).starttlsEnable(reqVO.getStarttlsEnable())
                .build();
        this.mailAccountRepository.save(mailAccount);
        return mailAccount;
    }

    @Override
    public MailAccount getMailAccountByUserId(Long userId) {
        return this.mailAccountRepository.findByUserMemberId(userId)
                .orElseThrow(() -> exception(USER_HAS_NOT_CREATEd_MAIL_ACCOUNT));
    }

    @Override
    public MailAccount getMailAccountByUsername(String username) {
        return this.mailAccountRepository.findByUsername(username)
                .orElseThrow(() -> exception(MAIL_ACCOUNT_NOT_FOUND));
    }


    @Override
    public List<MailAccount> getListMailAccount() {
        return this.mailAccountRepository.findAll();
    }
}
