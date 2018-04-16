package io.falcon.challenge.service;

import io.falcon.challenge.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSenderService.class);

    private final KafkaTemplate<String, ProductDTO> kafkaTemplate;
    private final WebSocketBroadcastService webSocketBroadcastService;

    public ProductSenderService(KafkaTemplate<String, ProductDTO> kafkaTemplate, WebSocketBroadcastService webSocketBroadcastService) {
        this.kafkaTemplate = kafkaTemplate;
        this.webSocketBroadcastService = webSocketBroadcastService;
    }

    /**
     * send productDto message to Kafka and connected clients
     *
     * @param topic      topicNames
     * @param productDTO dto
     */
    public void send(String topic, ProductDTO productDTO) {
        LOGGER.info("sending payload {}", productDTO);
        kafkaTemplate.send(topic, productDTO);
        sendNotificationToConnectedClients(productDTO);
    }

    private void sendNotificationToConnectedClients(ProductDTO productDTO) {
        String message = String.format("new products!<br/> name :%s <br/> description: %s", productDTO.getProductName(), productDTO.getProductDescription());
        try {
            webSocketBroadcastService.send(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

}
