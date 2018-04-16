package io.falcon.challenge.service;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.dto.TopicNames;
import org.springframework.stereotype.Service;

@Service
public class ProductJsonService {

    private final ProductSenderService productSenderService;
    private final TopicNames topicNames;

    public ProductJsonService(ProductSenderService productSenderService, TopicNames topicNames) {
        this.productSenderService = productSenderService;
        this.topicNames = topicNames;
    }

    /**
     * send product dto to related topic
     * @param productDTO dto
     */
    public void publish(ProductDTO productDTO) {
        productSenderService.send(topicNames.getProductTopic(), productDTO);
    }

}
