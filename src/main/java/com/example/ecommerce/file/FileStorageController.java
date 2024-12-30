package com.example.ecommerce.file;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/file")
@Tag(name = "File Storage")
public class FileStorageController {
    private final FileStorageService fileStorageService;

    @GetMapping
    @Operation(summary = "Doc file")
    public Resource loadFile(@RequestParam("path") String path) {
        return fileStorageService.load(path);
    }

    @PostMapping
    @Operation(summary = "Luu file")
    public CommonResult<String> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                           @RequestParam("representation") Representation rep) {
       return CommonResult.success(fileStorageService.save(multipartFile, rep).getPath());
    }

    @DeleteMapping
    @Operation(summary = "Xoa file")
    public CommonResult<Boolean> delete(@RequestParam("path") String path) {
        fileStorageService.delete(path);
        return CommonResult.success(true);
    }
}
