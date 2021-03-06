package io.falcon.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketBroadcastService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * simple broadcasting method to send message connected clients
     * @param message message
     */
    public void send(String message) {
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

}
