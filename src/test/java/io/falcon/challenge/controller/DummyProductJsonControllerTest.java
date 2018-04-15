package io.falcon.challenge.controller;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import io.falcon.challenge.service.DummyProductJsonService;
import io.falcon.challenge.service.RetrieveAllProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DummyProductJsonControllerTest {

    @InjectMocks
    private DummyProductJsonController dummyProductJsonController;

    @Mock
    private DummyProductJsonService dummyProductJsonService;

    @Mock
    private RetrieveAllProductService retrieveAllProductService;

    @Test
    public void shouldCreateDummyProduct() {
        DummyProductDTO productDTO = DummyProductDTOBuilder.aDummyProductDTO()
                .productName("name")
                .productName("name2").build();
        doNothing().when(dummyProductJsonService).publish(productDTO);
        ResponseEntity<DummyProductDTO> product = dummyProductJsonController.createProduct(productDTO);

        assertThat(product.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldGetProducts(){
        List<DummyProductDTO> dummyProductDTOS = Arrays.asList(
                DummyProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build(),
                DummyProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build(),
                DummyProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build(),
                DummyProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build()
        );

        when(retrieveAllProductService.getProducts()).thenReturn(dummyProductDTOS);

        ResponseEntity<List<DummyProductDTO>> products = dummyProductJsonController.getProducts();

        assertThat(products.getBody().size()).isEqualTo(dummyProductDTOS.size());
    }

}