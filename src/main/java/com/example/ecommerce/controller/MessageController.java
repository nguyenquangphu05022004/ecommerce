package com.example.ecommerce.controller;


import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.binding.MessageRequest;
import com.example.ecommerce.service.IMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;
    @PostMapping("/chat")
    public ResponseEntity<?> createMessage(
            @RequestParam("messageRequest") String request,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) throws JsonProcessingException {
        MessageRequest messageRequest = this.objectMapper.readValue(request, new TypeReference<MessageRequest>() {});
        messageRequest.setFiles(files);
        return ResponseEntity.ok(chatMessageService.createMessage(messageRequest));
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
