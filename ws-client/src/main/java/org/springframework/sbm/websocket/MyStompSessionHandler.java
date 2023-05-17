/*
 * Copyright 2021 - 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.sbm.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

/**
 * @author Fabian Krüger
 */
public class MyStompSessionHandler extends StompSessionHandlerAdapter {
    private StompSessionStore stompSessionStore;

    public MyStompSessionHandler(StompSessionStore stompSessionStore) {
        this.stompSessionStore = stompSessionStore;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Yeah, connected!");
        session.subscribe("/queue/recipes/applicable", new UpdateAvailableRecipes());
        session.subscribe("/queue/migration/logs", new MigrationLogsHandler());
        session.subscribe("/queue/migration/result", new MigrationResultHandler());
        session.send("/app/hello", "{\"name\":\"MyStompSessionHandler\"}");
    }

    private class MigrationLogsHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return String.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {

        }
    }
}
