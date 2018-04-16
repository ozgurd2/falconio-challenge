package io.falcon.challenge.consumer;

import io.falcon.challenge.dto.ProductDTOBuilder;
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

    private static final String PRODUCT_TOPIC = "producttopictest.t";

    @ClassRule
    public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, PRODUCT_TOPIC);

    @Autowired
    private ProductReceiverConsumer productReceiverConsumer;

    @Autowired
    private ProductSenderService productSenderService;

    @Before
    public void setup() throws Exception {
        kafkaEmbedded.before();
    }

    @Test
    public void testProductSendAndReceive() throws InterruptedException {
        productSenderService.send(PRODUCT_TOPIC, ProductDTOBuilder.aDummyProductDTO()
                .productName("testProduct")
                .productDescription("testProductNameDescription")
                .build());
        productReceiverConsumer.getLatch().await(3000, TimeUnit.MILLISECONDS);
        assertThat(productReceiverConsumer.getLatch().getCount()).isEqualTo(0);
    }

}