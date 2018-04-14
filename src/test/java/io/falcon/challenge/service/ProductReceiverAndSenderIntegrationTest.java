package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTOBuilder;
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

    private static final String HELLOWORLD_TOPIC = "producttopic.t";

    @ClassRule
    public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);

    @Autowired
    private ProductReceiver productReceiver;

    @Autowired
    private ProductSender productSender;

    @Test
    public void testProductSendAndReceive() throws InterruptedException {
        productSender.send(HELLOWORLD_TOPIC, DummyProductDTOBuilder.aDummyProductDTO()
                .productName("testProduct")
                .productDescription("testProductNameDescription")
                .build());
        productReceiver.getLatch().await(3000, TimeUnit.MILLISECONDS);
        assertThat(productReceiver.getLatch().getCount()).isEqualTo(0);
    }

}