package com.example.pouring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;
import com.tccc.kos.commons.core.dispatcher.annotations.RequestBody;
import com.tccc.kos.commons.util.concurrent.future.FutureWork;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@ApiController(base = "/selectandpour", title = "PouringController ")
public class PouringController {

    @Autowired
    private PouringService pouringService;

    @ApiEndpoint(POST = "/start", desc = "starts a pour")
    public FutureWork start() {
       FutureWork fw = pouringService.startPouring();
       return fw;
    }

    @ApiEndpoint(POST = "/stop", desc = "stops a pour")
    public void stop() {
        pouringService.stopPouring();
    }

    @ApiEndpoint(POST = "/selectsize", desc = "selects a size")
    public void selectSize(@RequestBody String size) {
        pouringService.setSize(size);
    }

    @ApiEndpoint(POST = "/selectbeverage", desc = "selects a beverage")
    public void selectBeverage(@RequestBody Beverage beverage) {
        pouringService.setSelectedBeverage(beverage);
    }

    @ApiEndpoint(GET ="/beverages", desc = "beverages list")
    public List<Beverage> beverages() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream input = new FileInputStream("coke-demo-prices.json");
            List<Beverage> beverages = mapper.readValue(input, new TypeReference<List<Beverage>>() {});
            return beverages;
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
