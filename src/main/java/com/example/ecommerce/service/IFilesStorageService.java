package com.example.ecommerce.service;

import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.domain.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface IFilesStorageService {
    void saveFile(MultipartFile file, Long entityId, EntityType entityType);
    Resource loadFileAsResource(String fileName, EntityType entityType);
    void deleteImage(FileEntity file);
    void deleteImage(Collection<? extends FileEntity> list);

}
