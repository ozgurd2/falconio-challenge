package io.falcon.challenge.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public  class TopicNames {

    @Value("${spring.kafka.topic.producttopic}")
    private String productTopic;

    public String getProductTopic() {
        return productTopic;
    }

    public void setProductTopic(String productTopic) {
        this.productTopic = productTopic;
    }
}
