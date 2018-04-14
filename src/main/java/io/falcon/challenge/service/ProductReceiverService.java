package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Listener class for kafka products
 * Integration test is written
 */
@Service
public class ProductReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReceiverService.class);

    private CountDownLatch latch = new CountDownLatch(1);

    /**
     * kafka listener for product data transfer object
     *
     * @param dummyProductDTO dto
     */
    @KafkaListener(topics = "${kafka.topic.producttopic}")
    public void receive(DummyProductDTO dummyProductDTO) throws InterruptedException {
        LOGGER.info("received payload {} ", dummyProductDTO.toString());
        Thread.sleep(1000L);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
