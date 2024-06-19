package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.user.UserRequest;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.domain.singleton.UserTrack;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.VerifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final IUserService userService;
    private final VerifyService verifyService;

    @GetMapping("/admin/users/role/{ROLE}")
    @ResponseBody
    public List<UserResponseInfo> getListUserByRole(@PathVariable("ROLE") Role role) {
        return userService.getListUserByRole(role);
    }

    @PostMapping("/register")
    public String registerAccount(@ModelAttribute UserRequest request) {
        userService.saveOrUpdate(request);
        return "redirect:/login";
    }

    @GetMapping("/admin/users")
    public String getListUser(Model model) {
        List<UserResponseInfo> userResponseInfos = userService.getAll();
        model.addAttribute("users", userResponseInfos);
        return "admin/user/list-users";
    }

    @PostMapping("/users")
    @ResponseBody
    public UserResponseInfo updateUserProfile(@RequestBody UserRequest request)   {
        return userService.saveOrUpdate(request);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPass,
                                 @RequestParam("newPassword") String newPass,
                                 @RequestParam("reNewPassword") String reNewPassword) {
        if (newPass.isEmpty() || reNewPassword.isEmpty() || !newPass.equals(reNewPassword)) {
            return "redirect:/change-password?error=true";
        } else {
            userService.changePassword(oldPass, newPass);
            return "redirect:/change-password?status=true";
        }
    }

    @GetMapping("/forget-password/new-pass")
    public String getPageNewPassword(@RequestParam("code") String code) {
        boolean codeExpire = verifyService.isCodeExpire(code);
        if (codeExpire) return "get-new-password";
        return "404";
    }

    @PostMapping("/forget-password/new-pass")
    public String updatePassword(@RequestParam("code") String code,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("reNewPassword") String reNewPassword,
                                 Model model) {
        if (reNewPassword.isEmpty() || newPassword.isEmpty() || !newPassword.equals(reNewPassword)) {
            model.addAttribute("message", "Vui lòng nhập đúng mật khẩu.");
            return "get-new-password";
        } else {
            boolean status = verifyService.update(code, newPassword);
            if (status) return "redirect:/login?true";
            else {
                model.addAttribute("message", "Mã đã hết hạn vui lòng gửi lại email.");
                return "redirect:/forget-password?expire=true";
            }
        }
    }
    @MessageMapping("/chat.getListUserConversation/{username}")
    @SendTo("/topic/public/listUserMessage/{username}")
    public UserInboxResponse getListUserConversation(@DestinationVariable("username") String username) {
        UserInboxResponse userResponse = userService.findByUsername(username);
        log.info("user: {}", userResponse);
        return userResponse;
    }

    @MessageMapping("/chat.addUser/{username}")
    public void userConnectedWebSocket(@DestinationVariable("username") String username) {
        UserTrack.getInstance().getMap().put(username, true);
    }

    @GetMapping("/users")
    @ResponseBody
    public UserInboxResponse getUserByUsername(@RequestParam("username") String username) {
        UserInboxResponse userResponse = userService.findByUsername(username);
        return userResponse;
    }
}
