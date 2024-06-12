package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.product.CategoryDto;
import com.example.ecommerce.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/add")
    public String getFormCategory(Model model) {
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("category", categoryDto);
        return "admin/category/create-categories";
    }
    @PostMapping("/categories/add")
    public String createCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.saveOrUpdate(categoryDto);
        return "redirect:/admin/categories";
    }
    @GetMapping("/categories/edit/{cateId}")
    public String getFormEditCategory(@PathVariable("cateId") Long cateId,
                                      Model model) {
        CategoryDto categoryDto = categoryService.findById(cateId);
        model.addAttribute("category", categoryDto);
        return "admin/category/edit-categories";
    }
    @PostMapping("/categories/edit")
    public String updateCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.saveOrUpdate(categoryDto);
        return "redirect:/admin/categories";
    }
    @GetMapping("/categories")
    public String getListCategories(Model model) {
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("categories", categoryDtos);
        return "admin/category/list-categories";
    }
    @GetMapping("/categories/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/admin/categories";
    }
}
