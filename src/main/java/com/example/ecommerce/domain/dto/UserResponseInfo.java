package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.Role;
import com.example.ecommerce.domain.UserContactDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserResponseInfo extends BaseDto {
    private String username;
    private String email;
    private ImageDto avatar;
    private Role role;
    private UserContactDetails userContactDetails;
    private List<EvaluationDto> evaluations = new ArrayList<>();
    private List<NotificationResponse> notifications;
    public String getDefaultImage() {
        if(this.avatar == null) {
            return "https://ssl.gstatic.com/accounts/ui/avatar_2x.png";
        }
        return "/files/image/" + this.avatar.getName();
    }
}
