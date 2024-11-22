package com.example.ecommerce.system.dal.dataobject.auth;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Table(name = "sys_auth_access_token")
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class AccessToken extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expires;
}
