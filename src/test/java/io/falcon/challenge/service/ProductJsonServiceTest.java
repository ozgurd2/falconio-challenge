package io.falcon.challenge.service;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.dto.ProductDTOBuilder;
import io.falcon.challenge.dto.TopicNames;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductJsonServiceTest {

    private static final String PRODUCT_TOPIC = "producttopictest.t";

    @InjectMocks
    private ProductJsonService productJsonService;

    @Mock
    private ProductSenderService productSenderService;

    @Mock
    private TopicNames topicNames;

    @Test
    public void shouldProductPublished() {
        ProductDTO productDTO = ProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build();
        when(topicNames.getProductTopic()).thenReturn(PRODUCT_TOPIC);
        productJsonService.publish(productDTO);
        verify(productSenderService).send(PRODUCT_TOPIC, productDTO);
    }

}