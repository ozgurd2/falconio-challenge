package io.falcon.challenge.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WebSocketBroadcastServiceTest {

    private static final String WEBSOCKET_DESTINATION = "/topic/messages";

    @InjectMocks
    private WebSocketBroadcastService webSocketBroadcastService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Test
    public void shouldSendMessage() {
        String message = "test";

        webSocketBroadcastService.send(message);

        verify(messagingTemplate).convertAndSend(WEBSOCKET_DESTINATION, message);

    }

}