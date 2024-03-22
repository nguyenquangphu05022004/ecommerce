package com.example.ecommerce.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    void saveFile(MultipartFile multipartFile);
    Resource loadFile(String fileName);

}
