package io.falcon.challenge.controller;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.service.DummyProductJsonService;
import io.falcon.challenge.service.RetrieveAllProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DummyProductJsonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyProductJsonController.class);

    private final DummyProductJsonService dummyProductJsonService;
    private final RetrieveAllProductService retrieveAllProductService;

    public DummyProductJsonController(DummyProductJsonService dummyProductJsonService, RetrieveAllProductService retrieveAllProductService) {
        this.dummyProductJsonService = dummyProductJsonService;
        this.retrieveAllProductService = retrieveAllProductService;
    }

    /**
     * POST /create -> Create new dummyProductDto
     *
     * @param dummyProductDTO data transfer object
     * @return Http/200;
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyProductDTO> createProduct(@RequestBody DummyProductDTO dummyProductDTO) {
        LOGGER.debug("Rest Request  to publish : {}", dummyProductDTO);
        dummyProductJsonService.publish(dummyProductDTO);
        return ResponseEntity.ok().build();
    }


    /**
     * GET /getProducts -> retrieve all products from database as json format
     * @return
     */
    @RequestMapping(value = "/getProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DummyProductDTO>> getProducts() {
        return new ResponseEntity<>(retrieveAllProductService.getProducts(), HttpStatus.OK);
    }

}
