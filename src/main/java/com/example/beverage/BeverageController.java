package com.example.beverage;

import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApiController(base = "/beverages", title = "BeveragesController")
public class BeverageController {
     @Autowired
     private BeverageService beverageService;
    @ApiEndpoint(GET = "/", desc = "beverages list")
    public List<Beverage> beverages() {
        return beverageService.getBeverages();
    }
}
