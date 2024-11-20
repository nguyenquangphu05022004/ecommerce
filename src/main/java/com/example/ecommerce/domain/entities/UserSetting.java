package com.example.ecommerce.domain.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserSetting extends BaseEntity{
    private boolean online;
    private boolean emailNotification;
    private boolean systemNotification;
}
