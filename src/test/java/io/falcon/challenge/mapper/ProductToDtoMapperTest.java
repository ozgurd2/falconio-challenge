package io.falcon.challenge.mapper;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class ProductToDtoMapperTest {

    @Test
    public void  shouldConvertDto(){
        Product product1 = new Product();
        product1.setProductDescription("descp1");
        product1.setProductName("name1");

        Product product2 = new Product();
        product2.setProductDescription("descp2");
        product2.setProductName("name2");

        Product product3 = new Product();
        product3.setProductDescription("descp3");
        product3.setProductName("name3");

        List<Product> products = Arrays.asList(product1, product2, product3);

        List<DummyProductDTO> dummyProductDTOS = ProductToDtoMapper.convert(products);

        assertThat(dummyProductDTOS.size()).isEqualTo(products.size());
        assertThat(dummyProductDTOS.get(2).getProductName()).isEqualTo(products.get(2).getProductName());
    }

}