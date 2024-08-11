package com.example.ecommerce.event.subscribe;

import com.example.ecommerce.event.listener.EmailListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.ecommerce.event.Event.EventType.*;
import static com.example.ecommerce.event.Event.getInstance;
@Component
@RequiredArgsConstructor
public class EmailSubscribe {
    private final EmailListener emailListener;

    @PostConstruct
    public void initSubscribe() {
        getInstance().subscribe(SEND_MAIL, emailListener.sendMail());
    }
}
