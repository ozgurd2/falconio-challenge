package io.falcon.challenge.service;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import io.falcon.challenge.dto.TopicNames;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DummyProductJsonServiceTest {

    @InjectMocks
    private DummyProductJsonService dummyProductJsonService;

    @Mock
    private ProductSenderService productSenderService;

    @Test
    public void shouldProductPublished() {
        DummyProductDTO dummyProductDTO = DummyProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build();
        dummyProductJsonService.publish(dummyProductDTO);
        verify(productSenderService).send(TopicNames.PRODUCT_TOPIC, dummyProductDTO);
    }

}