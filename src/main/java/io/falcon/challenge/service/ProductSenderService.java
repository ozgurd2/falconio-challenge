package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSenderService.class);

    private final KafkaTemplate<String, DummyProductDTO> kafkaTemplate;
    private final WebSocketBroadcastService webSocketBroadcastService;

    public ProductSenderService(KafkaTemplate<String, DummyProductDTO> kafkaTemplate, WebSocketBroadcastService webSocketBroadcastService) {
        this.kafkaTemplate = kafkaTemplate;
        this.webSocketBroadcastService = webSocketBroadcastService;
    }

    @Async
    public void send(String topic, DummyProductDTO dummyProductDTO) {
        LOGGER.info("sending payload {}", dummyProductDTO);
        kafkaTemplate.send(topic, dummyProductDTO);
        sendNotificationToConnectedClients(dummyProductDTO);
    }

    private void sendNotificationToConnectedClients(DummyProductDTO dummyProductDTO) {
        String message = String.format("new products!<br/> name :%s <br/> description: %s", dummyProductDTO.getProductName(), dummyProductDTO.getProductDescription());
        try {
            webSocketBroadcastService.send(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

}
