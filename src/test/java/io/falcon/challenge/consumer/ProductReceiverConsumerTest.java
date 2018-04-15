package io.falcon.challenge.consumer;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import io.falcon.challenge.entity.Product;
import io.falcon.challenge.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductReceiverConsumerTest {

    @InjectMocks
    private ProductReceiverConsumer productReceiverConsumer;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldSaveReceivedMessage() {
        DummyProductDTO productDTO = DummyProductDTOBuilder.aDummyProductDTO()
                .productName("name1")
                .productDescription("descp1")
                .build();

        Product product = new Product();
        product.setProductName("name1");
        product.setProductDescription("descp1");

        productReceiverConsumer.receive(productDTO);

        verify(productRepository).save(product);
    }


}