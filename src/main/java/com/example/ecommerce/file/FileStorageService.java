package com.example.ecommerce.file;

import com.example.ecommerce.frame.common.collection.CollUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface FileStorageService {

    FileEntity save(MultipartFile file, Representation representation);

    Resource load(String path);

    void delete(String path);

    default List<FileEntity> saveAll(List<MultipartFile> files, Representation representation) {
        if(CollUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return files.stream().map(s -> save(s, representation)).collect(Collectors.toList());
    }
}
