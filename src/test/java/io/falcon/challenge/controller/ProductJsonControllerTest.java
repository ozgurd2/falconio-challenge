package io.falcon.challenge.controller;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.dto.ProductDTOBuilder;
import io.falcon.challenge.service.ProductJsonService;
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
public class ProductJsonControllerTest {

    @InjectMocks
    private ProductJsonController productJsonController;

    @Mock
    private ProductJsonService productJsonService;

    @Mock
    private RetrieveAllProductService retrieveAllProductService;

    @Test
    public void shouldCreateDummyProduct() {
        ProductDTO productDTO = ProductDTOBuilder.aDummyProductDTO()
                .productName("name")
                .productName("name2").build();
        doNothing().when(productJsonService).publish(productDTO);
        ResponseEntity<Void> product = productJsonController.createProduct(productDTO);

        assertThat(product.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldGetProducts(){
        List<ProductDTO> productDTOS = Arrays.asList(
                ProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build(),
                ProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build(),
                ProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build(),
                ProductDTOBuilder.aDummyProductDTO().productName("name").productDescription("description").build()
        );

        when(retrieveAllProductService.getProducts()).thenReturn(productDTOS);

        ResponseEntity<List<ProductDTO>> products = productJsonController.getProducts();

        assertThat(products.getBody().size()).isEqualTo(productDTOS.size());
    }

}