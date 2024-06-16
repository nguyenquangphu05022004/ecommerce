package com.example.ecommerce.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFilesStorageService {
    void saveFile(MultipartFile multipartFile);
    Resource loadFile(String fileName);
    void deleteFile(String fileName);
    boolean fileIsExists(String fileName);

}
