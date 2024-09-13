package com.example.ecommerce.controller;


import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.binding.MessageRequest;
import com.example.ecommerce.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("${api.version}" + "/messenger")
@RestController
@CrossOrigin("*")
public class MessageController {
    private final IMessageService chatMessageService;

    @PostMapping("/chat")
    public ResponseEntity<?> createMessage(
            @RequestPart("request") MessageRequest request,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        request.setFiles(files);
        return ResponseEntity.ok(chatMessageService.createMessage(request));
    }

    @PostMapping("/messages/details")
    public ResponseEntity<?> getMessageDetails(
            @RequestBody FilterMessageRequest request
    ) {
        return ResponseEntity.ok(chatMessageService.getMessages(request));
    }

    @PostMapping("/messages/galleries")
    public ResponseEntity<?> getMessageGallery(
            @RequestParam(value = "limit", defaultValue = "100") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        return ResponseEntity.ok(chatMessageService.getMessageGallery(page, limit));
    }

}
