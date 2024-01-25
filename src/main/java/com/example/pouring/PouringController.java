package com.example.pouring;

import com.example.beverage.Beverage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;
import com.tccc.kos.commons.core.dispatcher.annotations.RequestBody;
import com.tccc.kos.commons.util.concurrent.future.FutureWork;
import lombok.extern.slf4j.Slf4j;

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

        ObjectMapper mapper = new ObjectMapper();

        final String jsonFilename = "cups.json";

        try(InputStream in = getClass().getClassLoader().getResourceAsStream(jsonFilename)) {
            log.debug("Reading json file: " + jsonFilename);
            log.debug("in: " + in);

            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            String jsonString = mapper.writeValueAsString(jsonNode);
            log.info(jsonString);

            List<Beverage> beverages = mapper.readValue(jsonString, new TypeReference<List<Beverage>>() {});
            return beverages;
        }
        catch(Exception e) {
            log.error("Error reading json file: " + jsonFilename);
            throw new RuntimeException(e);
        }
    }
}
