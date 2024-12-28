//package com.example.ecommerce.realtime.controller.chat;
//
//import com.example.ecommerce.frame.common.pojo.CommonResult;
//import com.example.ecommerce.realtime.controller.chat.vo.MessageCreateReqVO;
//import com.example.ecommerce.realtime.controller.chat.vo.MessageRespVO;
//import com.example.ecommerce.realtime.service.chat.MessageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import static com.example.ecommerce.frame.common.pojo.CommonResult.*;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/realtime/chat/messages")
//public class MessageController {
//
//    private final MessageService messageService;
//
//    @PostMapping
//    public CommonResult<MessageRespVO> createMessage(@RequestBody MessageCreateReqVO req) {
//        return success(new MessageRespVO(this.messageService.createMessage(req)));
//    }
//
//}
