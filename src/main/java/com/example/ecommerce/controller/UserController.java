package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
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
      if(newPass.isEmpty() || reNewPassword.isEmpty() || !newPass.equals(reNewPassword)) {
          return "redirect:/change-password?error=true";
      } else {
          userService.changePassword(oldPass, newPass);
          return "redirect:/change-password?status=true";
      }
    }
}
