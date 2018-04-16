package io.falcon.challenge.service;

import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.mapper.ProductToDtoMapper;
import io.falcon.challenge.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveAllProductService {

    private final ProductRepository productRepository;

    public RetrieveAllProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * get All products from database and converts to the data transfer object List.
     * @return productList
     */
    public List<ProductDTO> getProducts(){
        return ProductToDtoMapper.convert(productRepository.findAll(new Sort(Sort.Direction.DESC, "id")));
    }

}
