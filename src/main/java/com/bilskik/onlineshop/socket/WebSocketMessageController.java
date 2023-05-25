package com.bilskik.onlineshop.socket;

import com.bilskik.onlineshop.socket.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class WebSocketMessageController {

    @MessageMapping("/hello/siema")
    @SendTo("/topic/siema")
    public String message(Message message) {
        return message.toString();
    }
}
