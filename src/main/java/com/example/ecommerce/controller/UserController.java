package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String registerAccount(@ModelAttribute UserDto userDto) {
        userService.saveOrUpdate(userDto);
        return "redirect:/login";
    }
    @GetMapping("/admin/users")
    public String getListUser(Model model) {
        List<UserDto> userDtos = userService.records();
        model.addAttribute("users", userDtos);
        return "admin/user/list-users";
    }
}
