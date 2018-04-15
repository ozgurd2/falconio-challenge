package io.falcon.challenge.controller;

import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.service.DummyProductJsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DummyProductJsonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyProductJsonController.class);

    private final DummyProductJsonService dummyProductJsonService;

    public DummyProductJsonController(DummyProductJsonService dummyProductJsonService) {
        this.dummyProductJsonService = dummyProductJsonService;
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

}
