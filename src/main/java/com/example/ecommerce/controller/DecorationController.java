package com.example.ecommerce.controller;

import com.example.ecommerce.dto.DecorationDto;
import com.example.ecommerce.service.IDecorationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DecorationController {
    private final IDecorationService decorationService;

    @GetMapping("/admin/decorations")
    public String getFormCreateDecoration(Model model) {
        model.addAttribute("decoration", new DecorationDto());
        return "admin/decoration/create";
    }
    @PostMapping("/admin/decorations")
    public String getFormCreateDecoration(@ModelAttribute DecorationDto decorationDto) {
        decorationService.save(decorationDto);
        return "redirect:/admin/decorations";
    }
}
