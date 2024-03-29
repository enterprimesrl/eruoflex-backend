package com.example;

import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;

@ApiController(base = "/mycontroller", title = "This is a KOS controller")
public class MyController {
    @Autowired
    private MyService myService;

    @ApiEndpoint(GET = "/foo", desc = "This is an endpoint")
    public String foo() {
        return myService.bar();
    }
}
