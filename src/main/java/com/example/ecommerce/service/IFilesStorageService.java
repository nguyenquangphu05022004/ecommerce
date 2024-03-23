package com.example.ecommerce.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFilesStorageService {
    void saveFile(MultipartFile multipartFile, String folder);
    Resource loadFile(String fileName, String folder);
    void deleteFile(String fileName, String folder);

}
