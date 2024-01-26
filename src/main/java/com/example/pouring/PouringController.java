package com.example.pouring;

import com.example.beverage.Beverage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.HttpResponse;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;
import com.tccc.kos.commons.core.dispatcher.annotations.RequestBody;
import com.tccc.kos.commons.core.dispatcher.exceptions.NotFoundException;
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
    public FutureWork start() throws InterruptedException {
       FutureWork fw = pouringService.startPouring();
       return fw;
    }

    @ApiEndpoint(POST = "/stop", desc = "stops a pour")
    public void stop() {
        pouringService.stopPouring();
    }

    @ApiEndpoint(POST = "/selectsize", desc = "selects a size")
    public PouringSession selectSize(HttpResponse httpResponse, @RequestBody SelectCupRequest cup) {
        try {
            PouringSession session = pouringService.setSize(cup.getId());
            return session;
        } catch (NotFoundException e) {
            httpResponse.setStatus(404);
        }

        return null;
    }

    @ApiEndpoint(POST = "/selectbeverage", desc = "selects a beverage")
    public void selectBeverage(HttpResponse httpResponse, @RequestBody SelectBeverageRequest beverage) {
        try {
            pouringService.setSelectedBeverage(beverage.getId());
        } catch (NotFoundException e) {
            httpResponse.setStatus(404);
        }

    }
}
