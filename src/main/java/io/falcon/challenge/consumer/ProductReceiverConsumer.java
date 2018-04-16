package io.falcon.challenge.consumer;

import io.falcon.challenge.dto.ProductDTO;
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
public class ProductReceiverConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReceiverConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);//just for test purposes....

    private final ProductRepository productRepository;


    public ProductReceiverConsumer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * kafka listener for product data transfer object
     *
     * @param productDTO dto
     */
    @KafkaListener(topics = "${spring.kafka.topic.producttopic}")
    public void receive(ProductDTO productDTO)  {
        LOGGER.info("received payload {} ", productDTO);
        saveProduct(productDTO);
        latch.countDown();
    }

    private void saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductName(productDTO.getProductName());
        productRepository.save(product);
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
