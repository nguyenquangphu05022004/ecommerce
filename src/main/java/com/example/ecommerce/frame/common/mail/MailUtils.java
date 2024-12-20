package com.example.ecommerce.frame.common.mail;

import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;

public class MailUtils {
    public static void sendEmail(MailAccount mailAccount, String title, String content,  String toMail) throws Exception{
            Session session = Session.getInstance(mailAccount.buildProperties(), mailAccount.buildAuthenticator());
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(mailAccount.getUsername()));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            msg.setSubject(title, "UTF-8");
            msg.setText(content, "UTF-8");
            msg.setSentDate(new Date());
            Transport.send(msg);
    }
}
