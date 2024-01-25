package com.example.cup;

import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ApiController(base = "/cups", title = "CupController")
public class CupController {
    @Autowired
    private CupService cupService;
    @ApiEndpoint(GET = "/", desc = "cup list")
    public List<Cup> cups() {
        return cupService.getCups();
    }
}

