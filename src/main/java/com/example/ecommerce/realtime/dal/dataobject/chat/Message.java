package com.example.ecommerce.realtime.dal.dataobject.chat;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "realtime_chat_message")
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
public class Message extends BaseEntity {
   private String content;
   @ManyToOne
   @JoinColumn(name = "from_user_id")
   private UserMember fromUser;
   @ManyToOne
   @JoinColumn(name = "to_user_id")
   private UserMember toUser;

   private Boolean readMessage;

   @Convert(converter = JsonListConverter.class)
   private List<String> mediaUrls;

   @ManyToOne
   @JoinColumn(name = "reply_message_id")
   private Message replyMessage;

   private Boolean revokeMessage;

   @Transient
    public Position getPosition() {
       Long loginUserId = SecurityUtils.getLoginUserMemberId();
       if(loginUserId.equals(fromUser.getId())) {
           return Position.RIGHT;
       }
       return Position.LEFT;
   }

   @Transient
   public String getSender() {
      Long loginUserId = SecurityUtils.getLoginUserMemberId();
      if(loginUserId.equals(fromUser.getId())) {
         return "You";
      }
      return fromUser.getFullName();
   }
}
