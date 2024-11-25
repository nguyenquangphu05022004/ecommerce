package com.example.ecommerce.file;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import com.example.ecommerce.frame.common.string.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService{

    private final FileRepository fileRepository;
    private final String PATH = "/uploads";
    @Override
    public FileEntity save(MultipartFile file, Representation representation) {
        String url = PATH;
        File f = new File(url);
        if(!f.exists() && f.mkdir());
        url += "/" + representation.name();
        f = new File(url);
        if(!f.exists() && f.mkdir());

        String extension = StringUtils.getFromLastSubStr(".", file.getOriginalFilename());

        url = url + "/" + UUID.randomUUID() + "." + extension;

        FileEntity fileEntity = new FileEntity(
                file.getOriginalFilename(),
                url,
                representation
        );

        this.fileRepository.save(fileEntity);

        return fileEntity;
    }

    @Override
    public Resource load(String path) {
        Path p = Path.of(path);
        if(Files.exists(p)) {
            try {
                return new ByteArrayResource(Files.readAllBytes(p));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Optional<FileEntity> opFile = this.fileRepository.findByPath(path);
        if(opFile.isPresent()) {
            this.fileRepository.delete(opFile.get());
        }
        return null;
    }

    @Override
    public void delete(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
            Optional<FileEntity> op = this.fileRepository.findByPath(path);
            if(op.isPresent()) {
                this.fileRepository.delete(op.get());
            }
        } catch (IOException e) {

        }
    }

}
