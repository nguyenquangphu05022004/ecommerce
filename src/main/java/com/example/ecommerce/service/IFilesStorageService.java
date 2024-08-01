package com.example.ecommerce.service;

import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.file.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface IFilesStorageService {
    FileEntity saveFile(MultipartFile file, Long entityId, FileEntityType fileEntityType);
    Resource loadFileAsResource(String fileName, FileEntityType fileEntityType);
    void deleteImage(FileEntity file);
    void deleteImage(Collection<? extends FileEntity> list);

}
