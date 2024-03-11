package com.example.ecommerce.controller;

import com.example.ecommerce.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Chức năng admin:
     *      1, Tạo các thông tin các entity
     *      2, Cập nhật role của người dùng khi muốn thành người bán hàng
     *      3,
     *
     ***/
    @GetMapping("/home")
    public String getHome() {
        return "admin/admin-control";
    }

}
