package io.falcon.challenge.mapper;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import io.falcon.challenge.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductToDtoMapper {

    public static List<DummyProductDTO> convert(List<Product> products) {
        return  products.stream().map(product -> DummyProductDTOBuilder.aDummyProductDTO()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .build()).collect(Collectors.toList());
    }

}
