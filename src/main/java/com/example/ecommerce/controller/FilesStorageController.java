package com.example.ecommerce.controller;

import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilesStorageController {

    private final IUserService userService;
    private final IFilesStorageService filesStorageService;
    @PostMapping("/files/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        userService.updateAvatar(file);
        return "redirect:/user/profile";
    }
    @GetMapping(value = "/files/{type}/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public Resource getImage(
            @PathVariable("type") String type,
            @PathVariable("fileName") String fileName) {
        Resource resource = filesStorageService.loadFile(fileName);
        return resource;
    }


}
