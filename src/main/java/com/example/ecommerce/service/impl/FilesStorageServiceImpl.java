package com.example.ecommerce.service.impl;

import com.example.ecommerce.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("src\\main\\resources\\static\\uploads");
    @Override
    public void saveFile(MultipartFile multipartFile) {

    }

    @Override
    public Resource loadFile(String fileName) {
        return null;
    }
}
