package org.springframework.sbm.websocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;

class WsClientApplicationTests {

    @Test
    void test_renameMe() throws ExecutionException, InterruptedException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
        TaskScheduler taskScheduler = new DefaultManagedTaskScheduler();
        stompClient.setTaskScheduler(taskScheduler); // for heartbeats
        String url = "ws://127.0.0.1:8080/endpoint";
        StompSessionHandler sessionHandler = new MyStompSessionHandler(new StompSessionStore());
        ListenableFuture<StompSession> futureSession = stompClient.connect(url, sessionHandler);
        futureSession.get();
        Thread.sleep(10000);
    }

}
