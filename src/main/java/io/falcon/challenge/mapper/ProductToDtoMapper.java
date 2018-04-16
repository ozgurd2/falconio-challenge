package io.falcon.challenge.mapper;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.dto.ProductDTOBuilder;
import io.falcon.challenge.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductToDtoMapper {

    public static List<ProductDTO> convert(List<Product> products) {
        return  products.stream().map(product -> ProductDTOBuilder.aDummyProductDTO()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .build()).collect(Collectors.toList());
    }

}
