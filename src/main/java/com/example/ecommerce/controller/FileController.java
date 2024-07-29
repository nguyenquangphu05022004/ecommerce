package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin("*")

public class FileController {

    private final IFilesStorageService filesStorageService;

    @GetMapping(value = "/images/{fileType}/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> loadFile(
            @PathVariable("fileType") EntityType fileType,
            @PathVariable("fileName") String fileName
    ) {
        return ResponseEntity.ok(filesStorageService.loadFileAsResource(fileName, fileType));
    }
}
