package io.falcon.challenge.controller;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import io.falcon.challenge.service.DummyProductJsonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;


@RunWith(MockitoJUnitRunner.class)
public class DummyProductJsonControllerTest {

    @InjectMocks
    private DummyProductJsonController dummyProductJsonController;

    @Mock
    private DummyProductJsonService dummyProductJsonService;

    @Test
    public void shouldCreateDummyProduct() {
        DummyProductDTO productDTO = DummyProductDTOBuilder.aDummyProductDTO()
                .productName("name")
                .productName("name2").build();
        doNothing().when(dummyProductJsonService).publish(productDTO);
        ResponseEntity<DummyProductDTO> product = dummyProductJsonController.createProduct(productDTO);

        assertThat(product.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}