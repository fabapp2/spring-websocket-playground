package org.springframework.sbm.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class WsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsClientApplication.class, args);
    }

}
