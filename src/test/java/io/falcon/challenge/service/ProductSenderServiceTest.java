package io.falcon.challenge.service;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.dto.ProductDTOBuilder;
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
    private KafkaTemplate<String, ProductDTO> kafkaTemplate;

    @Test
    public void shouldSendMessageToKafkaAndWebSocket() throws Exception {
        ProductDTO productDTO = ProductDTOBuilder.aDummyProductDTO().productName("n").productDescription("d").build();
        String message = String.format("new products!<br/> name :%s <br/> description: %s", productDTO.getProductName(), productDTO.getProductDescription());

        productSenderService.send("topic", productDTO);

        verify(kafkaTemplate).send("topic", productDTO);
        verify(webSocketBroadcastService).send(message);
    }

}