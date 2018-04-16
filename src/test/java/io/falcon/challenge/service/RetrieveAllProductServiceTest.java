package io.falcon.challenge.service;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.entity.Product;
import io.falcon.challenge.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveAllProductServiceTest {

    @InjectMocks
    private RetrieveAllProductService retrieveAllProductService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldGetAllProducts(){
        Product product = new Product();
        product.setProductName("name");
        product.setProductDescription("description");

        when(productRepository.findAll(new Sort(Sort.Direction.DESC, "id"))).thenReturn(Arrays.asList(product));

        List<ProductDTO> products = retrieveAllProductService.getProducts();

        assertThat(products.get(0).getProductName()).isEqualTo(product.getProductName());
        assertThat(products.get(0).getProductDescription()).isEqualTo(product.getProductDescription());
    }

}