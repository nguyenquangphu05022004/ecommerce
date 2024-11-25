package com.example.ecommerce.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    FileEntity save(MultipartFile file, Representation representation);
    Resource load(String path);
    void delete(String path);
}
