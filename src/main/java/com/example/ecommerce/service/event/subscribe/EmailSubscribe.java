package com.example.ecommerce.service.event.subscribe;

import com.example.ecommerce.service.event.listener.EmailListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.ecommerce.service.event.Event.EventType.*;
import static com.example.ecommerce.service.event.Event.getInstance;
@Component
@RequiredArgsConstructor
public class EmailSubscribe {
    private final EmailListener emailListener;

    @PostConstruct
    public void initSubscribe() {
        getInstance().subscribe(SEND_MAIL, emailListener.sendMail());
    }
}
