package com.example.ecommerce.controller;

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

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> loadFile(
           @RequestParam("url") String url
    ) {
        return ResponseEntity.ok(filesStorageService.loadFileAsResource(url));
    }
}
