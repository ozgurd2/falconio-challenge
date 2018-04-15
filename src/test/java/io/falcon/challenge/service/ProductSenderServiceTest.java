package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ProductSenderServiceTest {

    @InjectMocks
    private ProductSenderService productSenderService;

    @Mock
    private WebSocketBroadcastService webSocketBroadcastService;

    @Mock
    private KafkaTemplate<String, DummyProductDTO> kafkaTemplate;

    @Test
    public void shouldSendMessageToKafkaAndWebSocket() throws Exception {
        DummyProductDTO dummyProductDTO = DummyProductDTOBuilder.aDummyProductDTO().productName("n").productDescription("d").build();
        String message = String.format("new products!<br/> name :%s <br/> description: %s", dummyProductDTO.getProductName(), dummyProductDTO.getProductDescription());

        productSenderService.send("topic", dummyProductDTO);

        verify(kafkaTemplate).send("topic", dummyProductDTO);
        verify(webSocketBroadcastService).send(message);
    }

}