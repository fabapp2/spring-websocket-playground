package org.springframework.sbm.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.time.Instant;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class WebsocketClientController {

    private SimpMessagingTemplate template;
    private WebSocketStompClient webSocketStompClient;

    @Autowired
    public WebsocketClientController(SimpMessagingTemplate template, WebSocketStompClient webSocketStompClient) {
        this.template = template;
        this.webSocketStompClient = webSocketStompClient;
    }

    @SubscribeMapping("/topic/greetings")
    public void greet(Message m) {

        String text = "[" + getTimestamp() + "]:" + m.getPayload();
        this.template.convertAndSend("/topic/greetings", new HelloMessageModel("Hello, hello"));
    }


    private String getTimestamp() {
        return Instant.now().toString();
    }

}