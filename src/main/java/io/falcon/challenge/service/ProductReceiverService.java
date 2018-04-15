package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.entity.Product;
import io.falcon.challenge.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Listener class for kafka products
 * Integration test is written ProductReceiverAndSenderIntegrationTest
 */
@Service
public class ProductReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReceiverService.class);

    private CountDownLatch latch = new CountDownLatch(1);//just for test purposes....

    private final ProductRepository productRepository;


    public ProductReceiverService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * kafka listener for product data transfer object
     *
     * @param dummyProductDTO dto
     */
    @KafkaListener(topics = "${spring.kafka.topic.producttopic}")
    public void receive(DummyProductDTO dummyProductDTO) throws InterruptedException {
        LOGGER.info("received payload {} ", dummyProductDTO);
        Thread.sleep(1000L);
        saveProduct(dummyProductDTO);
        latch.countDown();
    }

    private void saveProduct(DummyProductDTO dummyProductDTO) {
        Product product = new Product();
        product.setProductDescription(dummyProductDTO.getProductDescription());
        product.setProductName(dummyProductDTO.getProductName());
        productRepository.save(product);
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
