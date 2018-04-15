package io.falcon.challenge;

import io.falcon.challenge.dto.DummyProductDTOBuilder;
import io.falcon.challenge.dto.TopicNames;
import io.falcon.challenge.service.ProductReceiverService;
import io.falcon.challenge.service.ProductSenderService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ProductReceiverAndSenderIntegrationTest {

    @ClassRule
    public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, TopicNames.PRODUCT_TOPIC);

    @Autowired
    private ProductReceiverService productReceiverService;

    @Autowired
    private ProductSenderService productSenderService;

    @Before
    public void setup() throws Exception {
        kafkaEmbedded.before();
    }

    @Test
    public void testProductSendAndReceive() throws InterruptedException {
        productSenderService.send(TopicNames.PRODUCT_TOPIC, DummyProductDTOBuilder.aDummyProductDTO()
                .productName("testProduct")
                .productDescription("testProductNameDescription")
                .build());
        productReceiverService.getLatch().await(3000, TimeUnit.MILLISECONDS);
        assertThat(productReceiverService.getLatch().getCount()).isEqualTo(0);
    }

}