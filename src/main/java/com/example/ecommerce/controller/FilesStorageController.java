package com.example.ecommerce.controller;

import com.example.ecommerce.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilesStorageController {

    private final FilesStorageService storageService;

    @Autowired
    public FilesStorageController(FilesStorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/files/uploads")
    public void uploadFile(@RequestParam("file")MultipartFile file) {

    }


}
