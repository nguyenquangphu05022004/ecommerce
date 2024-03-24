package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.Verify;
import com.example.ecommerce.service.VerifyService;
import com.example.ecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserServiceImpl userService;
    private final VerifyService verifyService;

    @Autowired
    public UserController(UserServiceImpl userService, VerifyService verifyService) {
        this.userService = userService;
        this.verifyService = verifyService;
    }


    @GetMapping("/admin/users/role/{ROLE}")
    @ResponseBody
    public List<UserDto> getListUserByRole(@PathVariable("ROLE") Role role) {
        return userService.getListUserByRole(role);
    }

    @PostMapping("/register")
    public String registerAccount(@ModelAttribute UserDto userDto) {
        userService.saveOrUpdate(userDto);
        return "redirect:/home";
    }

    @GetMapping("/admin/users")
    public String getListUser(Model model) {
        List<UserDto> userDtos = userService.records();
        model.addAttribute("users", userDtos);
        return "admin/user/list-users";
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
}
