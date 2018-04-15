package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.TopicNames;
import org.springframework.stereotype.Service;

@Service
public class DummyProductJsonService {

    private final ProductSenderService productSenderService;

    public DummyProductJsonService(ProductSenderService productSenderService) {
        this.productSenderService = productSenderService;
    }

    public void publish(DummyProductDTO dummyProductDTO) {
        productSenderService.send(TopicNames.PRODUCT_TOPIC, dummyProductDTO);
    }

}
