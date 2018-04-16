package io.falcon.challenge.controller;

import io.falcon.challenge.dto.ErrorDTO;
import io.falcon.challenge.dto.ProductDTO;
import io.falcon.challenge.service.ProductJsonService;
import io.falcon.challenge.service.RetrieveAllProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductJsonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductJsonController.class);

    private final ProductJsonService productJsonService;
    private final RetrieveAllProductService retrieveAllProductService;

    public ProductJsonController(ProductJsonService productJsonService, RetrieveAllProductService retrieveAllProductService) {
        this.productJsonService = productJsonService;
        this.retrieveAllProductService = retrieveAllProductService;
    }

    /**
     * POST /create -> Create new dummyProductDto
     *
     * @param productDTO data transfer object
     * @return Http/200;
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody @Validated ProductDTO productDTO) {
        LOGGER.debug("Rest Request  to publish : {}", productDTO);
        productJsonService.publish(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * GET /getProducts -> retrieve all products from database as json format
     *
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return new ResponseEntity<>(retrieveAllProductService.getProducts(), HttpStatus.OK);
    }

    /**
     * Controller Advice to handle exceptions
     * @param e  kind a exception
     * @return error dto
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}