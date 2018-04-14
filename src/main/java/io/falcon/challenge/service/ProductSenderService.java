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

    public ProductSenderService(KafkaTemplate<String, DummyProductDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void send(String topic, DummyProductDTO dummyProductDTO) {
        LOGGER.info("sending payload {}", dummyProductDTO.toString());
        kafkaTemplate.send(topic, dummyProductDTO);
    }

}
